package com.rokid.skill.kit4j.core;

import com.rokid.skill.kit4j.constants.ReqAttrName;
import com.rokid.skill.kit4j.exception.DefaultExceptionHandler;
import com.rokid.skill.kit4j.exception.ExceptionDispatcher;
import com.rokid.skill.kit4j.exception.SkillException;
import com.rokid.skill.kit4j.util.JacksonUtil;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.response.ResResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 语音业务请求入口
 *
 * @author wuyukai
 */
@Slf4j
@CrossOrigin
@RestController
public class SkillController {

  @Value("${info.app.version}")
  private String serviceVersion;

  /**
   * 业务分发器
   */
  private final SpeechletDispatcher speechletDispatcher;

  private final DefaultExceptionHandler defaultExceptionHandler;
  private final ExceptionDispatcher exceptionDispatcher;

  @Autowired
  public SkillController(SpeechletDispatcher speechletDispatcher,
    DefaultExceptionHandler defaultExceptionHandler,
    @Autowired(required = false) ExceptionDispatcher exceptionDispatcher) {
    this.speechletDispatcher = speechletDispatcher;
    this.defaultExceptionHandler = defaultExceptionHandler;
    this.exceptionDispatcher = exceptionDispatcher;
  }

  /**
   * 设备调用统一入口
   *
   * @param request 请求
   * @return 响应json字符串
   * @throws Exception 异常
   */
  @RequestMapping(path = "/speechlet", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public String handleRequest(HttpServletRequest request, HttpServletResponse response)
    throws Exception {
    // 原始的请求数据对象
    ReqRequest reqRequest = (ReqRequest) request.getAttribute(ReqAttrName.REQ_REQUEST);
    request.setAttribute(ReqAttrName.STATUS, String.valueOf(HttpStatus.OK.value()));
    request.setAttribute(ReqAttrName.SERVICE_VERSION, serviceVersion);
    // 进行业务分发
    ResResponse resResponse;
    try {
      resResponse = speechletDispatcher.handle(reqRequest);
    } catch (Exception e) {
      log.error("method invoke happened an error", e);
      if (exceptionDispatcher != null && (e.getCause() instanceof SkillException || e instanceof SkillException)) {
        SkillException skillException= (SkillException) e.getCause();
        log.info("e is: {}", e);
        if (e instanceof SkillException) {
          skillException = (SkillException) e;
        }
        request.setAttribute(ReqAttrName.STATUS, skillException.getCode().getCode());
        request.setAttribute(ReqAttrName.EXCEPTION, ExceptionUtils.getStackTrace(skillException));
        resResponse = exceptionDispatcher.handleException(reqRequest, skillException);
        exceptionDispatcher.reportException(reqRequest, skillException);
      } else {
        //抛给全局异常拦截
        request.setAttribute(ReqAttrName.STATUS,
          String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        throw e;
      }
    }
    String result = JacksonUtil.toJson(resResponse);
    log.info("RESPONSE : {}", result);
    request.setAttribute(ReqAttrName.RESULT, result);
    return result;
  }

  @ResponseBody
  @ExceptionHandler(value = Exception.class)
  public ResResponse defaultErrorHandler(
    HttpServletRequest request, HttpServletResponse response, Exception ex) {
    return defaultExceptionHandler.errorHandler(request, response, ex);
  }

}
