package com.rokid.skill.kit.controller;

import static com.rokid.skill.kit.constants.Const.HEADER_NAME_REAL_IP;
import static com.rokid.skill.kit.constants.Const.STATUS_FAILED;
import static com.rokid.skill.kit.constants.Const.STATUS_SUCCESS;

import com.google.common.base.Stopwatch;
import com.rokid.skill.kit.bean.ServiceLog;
import com.rokid.skill.kit.bean.SpeechletLog;
import com.rokid.skill.kit.bean.UserRecord;
import com.rokid.skill.kit.core.ExtraInfo;
import com.rokid.skill.kit.core.SpeechletDispatcher;
import com.rokid.skill.kit.exception.ExceptionDispatcher;
import com.rokid.skill.kit.exception.SkillException;
import com.rokid.skill.kit.exception.SkillKitCode;
import com.rokid.skill.kit.service.LogRecorderService;
import com.rokid.skill.kit.service.UserRecordService;
import com.rokid.skill.kit.util.JacksonUtil;
import com.rokid.skill.kit.util.LogRecorderUtil;
import com.rokid.skill.kit.util.ResponseUtil;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.response.ResResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 语音业务请求入口.
 *
 * @author wuyukai
 */
@Slf4j
@CrossOrigin
@RestController
public class SkillController {

  @Value("${info.app.version}")
  private String serviceVersion;

  @Value("${spring.application.name}")
  private String serviceName;

  /**
   * 业务分发.
   */
  private final LogRecorderService logRecorderService;

  private final UserRecordService userRecordService;

  private final SpeechletDispatcher speechletDispatcher;

  private final ExceptionDispatcher exceptionDispatcher;

  /**
   * Skill 请求构造方法.
   */
  @Autowired
  public SkillController(SpeechletDispatcher speechletDispatcher,
      @Autowired(required = false) ExceptionDispatcher exceptionDispatcher,
      LogRecorderService logRecorderService,
      UserRecordService userRecordService) {
    this.speechletDispatcher = speechletDispatcher;
    this.exceptionDispatcher = exceptionDispatcher;
    this.logRecorderService = logRecorderService;
    this.userRecordService = userRecordService;
  }

  /**
   * 设备调用统一入口.
   *
   * @param request 请求
   * @return 响应 json 字符串
   */
  @RequestMapping(path = "/speechlet", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResResponse handleRequest(HttpServletRequest request) throws Exception {

    // 设置请求时间
    Stopwatch stopwatch = Stopwatch.createStarted();
    String gmtCreate = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

    Map<String, String> requestHeader = readRequestHeaders(request);

    String requestIp = request.getHeader(HEADER_NAME_REAL_IP);
    String body = getBodyFromRequest(request);
    log.info("REQUEST BODY: {}", body);

    // 获取请求体
    ReqRequest reqRequest = JacksonUtil.toObject(body, ReqRequest.class);
    if (reqRequest == null) {
      log.error("error code : {}, request body is invalid : {}", SkillKitCode.INVALID_BODY.value(),
          body);
      return ResponseUtil.buildIngoreEventResponse();
    }

    // 进行业务分发
    ResResponse resResponse = null;
    String status = STATUS_SUCCESS;
    String exception = StringUtils.EMPTY;
    try {
      resResponse = speechletDispatcher.handle(reqRequest);
    } catch (Exception e) {
      SkillException skillException = null;
      if (exceptionDispatcher != null) {
        if (e.getCause() instanceof SkillException) {
          skillException = (SkillException) e.getCause();
          log.error("skill handle happen an error, code : {}, message : {}, cause :",
              skillException.getCode(), skillException.getMessage(), e);
        } else if (e instanceof SkillException) {
          skillException = (SkillException) e;
          log.error("skill handle happen an error, code : {}, message : {}, cause :",
              skillException.getCode(), skillException.getMessage(), e);
        }

        if (skillException != null) {
          status = String.valueOf(skillException.getCode().value());
          exception = ExceptionUtils.getStackTrace(skillException);
          resResponse = exceptionDispatcher.handleException(reqRequest, skillException);
          exceptionDispatcher.reportException(reqRequest, skillException);
        }
      }

      if (resResponse == null) {
        log.error("skill handle happen an error, {}, {}", e, e.getCause());
        resResponse = ResponseUtil.buildIngoreEventResponse();
        status = STATUS_FAILED;
        exception = ExceptionUtils.getStackTrace(e);
      }
    } finally {
      String result = JacksonUtil.toJson(resResponse);
      log.info("RESPONSE : {}", result);
      Long costsTime = stopwatch.elapsed(TimeUnit.MILLISECONDS);
      log.info("TOTAL COSTS = {}ms", costsTime);
      ServiceLog sl = LogRecorderUtil
          .buildServiceLog(body, result, serviceName, serviceVersion, requestHeader, status,
              exception, requestIp, gmtCreate, costsTime);
      logRecorderService.recordServiceLog(sl);
      SpeechletLog mrl = LogRecorderUtil
          .buildSpeechletLog(reqRequest, resResponse, status, gmtCreate, costsTime);
      logRecorderService.recordSpeechletLog(mrl);
      UserRecord userRecord = LogRecorderUtil.buildUserRecord(reqRequest);
      userRecordService.record(userRecord);
      MDC.clear();
      ExtraInfo.clear();
    }

    return resResponse;
  }

  private Map<String, String> readRequestHeaders(HttpServletRequest request) {
    Map<String, String> headers = new HashMap<>(8);
    Enumeration<String> headerNames = request.getHeaderNames();
    if (headerNames != null) {
      while (headerNames.hasMoreElements()) {
        String headerName = headerNames.nextElement();
        String headerValue = request.getHeader(headerName);
        headers.put(headerName, headerValue);
      }
    }
    return headers;
  }

  private String getBodyFromRequest(HttpServletRequest request) throws IOException {
    try (BufferedReader reader = request.getReader()) {
      return reader.lines().map(String::trim).filter(s -> !s.isEmpty())
          .collect(Collectors.joining());
    }
  }
}
