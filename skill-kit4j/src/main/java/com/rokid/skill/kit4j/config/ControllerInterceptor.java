package com.rokid.skill.kit4j.config;

import com.rokid.skill.kit4j.constants.Const;
import com.rokid.skill.kit4j.constants.ReqAttrName;
import com.rokid.skill.kit4j.core.Code;
import com.rokid.skill.kit4j.exception.KitException;
import com.rokid.skill.kit4j.log.LogFactory;
import com.rokid.skill.kit4j.log.LogService;
import com.rokid.skill.kit4j.log.LogStatistics;
import com.rokid.skill.kit4j.log.ServiceLog;
import com.rokid.skill.kit4j.util.JacksonUtil;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.request.request.content.enums.RokidActionTypeEnums;
import com.rokid.skill.protocol.utils.ReqBasicInfo;
import com.rokid.skill.protocol.utils.RequestUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


/**
 * 控制拦截器，在config类中注入了LogRecorder，在此处不注入，限制skill使用该拦截器
 *
 * @author Bassam
 */
@Slf4j
class ControllerInterceptor extends HandlerInterceptorAdapter {

  @Autowired(required = false)
  private LogService logService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
    Object handler)
    throws Exception {

    // 设置请求时间
    Long beginTime = System.currentTimeMillis();
    request.setAttribute(ReqAttrName.REQ_BEGIN_TIME, beginTime);

    String serviceName = request.getRequestURI().substring(request.getContextPath().length());
    if (StringUtils.isBlank(serviceName)) {
      log.error("serviceNameError:serviceName is blank");
      throw new ServletException("ServiceNameError");
    }

    // 设置服务名称
    request.setAttribute(ReqAttrName.SERVICE_NAME, serviceName);

    String headers = readRequestHeaders(request).toString();
    request.setAttribute(ReqAttrName.REQ_HEADER, headers);
    String body = getBodyFromRequest(request);

    if (StringUtils.isBlank(body)) {
      log.error("request body is empty");
      throw new KitException(Code.PROTOCOL_ERROR, "body is empty");
    }
    request.setAttribute(ReqAttrName.REQ_BODY, body);
    // 处理请求数据
    processRokidProtocolData(request, body);
    // 数据处理
    return super.preHandle(request, response, handler);
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
    ModelAndView modelAndView) throws Exception {
    Long beginTime = (Long) request.getAttribute(ReqAttrName.REQ_BEGIN_TIME);
    Long costsTime = System.currentTimeMillis() - beginTime;
    request.setAttribute(ReqAttrName.REQ_COST_TIME, costsTime);
    log.info("TOTAL COSTS = {}ms", costsTime);
    if (logService != null){
      ServiceLog sl = LogFactory.buildServiceLog(request);
      logService.recordServiceLog(sl);
    }
    LogStatistics.removeContext();
    super.postHandle(request, response, handler, modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
    Object handler, Exception ex) throws Exception {
    MDC.clear();
    super.afterCompletion(request, response, handler, ex);
  }

  /**
   * 数据预处理
   */
  private void processRokidProtocolData(HttpServletRequest request, String body)
    throws KitException {
    ReqRequest reqRequest;
    // 获取请求体
    reqRequest = JacksonUtil.toObject(body, ReqRequest.class);
    if (reqRequest == null) {
      log.error("error code : [{}], parse reqRequest error", Code.PROTOCOL_ERROR.getCode());
      throw new KitException(Code.PROTOCOL_ERROR, "body parse error");
    }

    if (reqRequest.getContext() == null) {
      log
        .error("error code : [{}], reqContext not allowed to be empty",
          Code.PROTOCOL_ERROR.getCode());
      throw new KitException(Code.PROTOCOL_ERROR, "ReqContext error");
    }
    // 基本请求信息
    ReqBasicInfo reqBasicInfo = RequestUtils.getBasicInfo(reqRequest);
    String requestId = reqBasicInfo.getRequestId();
    if (StringUtils.isBlank(requestId)) {
      requestId = UUID.randomUUID().toString().replace("-", "").toUpperCase();
      log.warn("no requestId, replace it by new uuid : {}", requestId);
      reqRequest.getRequest().setReqId(requestId);
      reqBasicInfo.setRequestId(requestId);
    }
    MDC.put(Const.TRACE_ID, requestId);
    log.info("REQUEST BODY: {}", body);
    if (StringUtils.isBlank(reqBasicInfo.getActionType())
      || RokidActionTypeEnums.convert(reqBasicInfo.getActionType()) == null || StringUtils
      .isBlank(reqBasicInfo.getActionName())) {
      log.error("ReqBasicInfo error, action type is not allowed or action name is empty");
      throw new KitException(Code.PROTOCOL_ERROR,
        "ReqBasicInfo error, action type is not allowed or action name is empty");
    }
    if (reqRequest.getRequest() == null) {
      log.error("ReqRequestValue error, request value not allowed to be empty");
      throw new KitException(Code.PROTOCOL_ERROR,
        "ReqRequestValue error, request value not allowed to be empty");
    }

    if (reqRequest.getContext() == null) {
      log.error("error code : [{}], ReqContext error, request context not allowed to be empty",
        Code.PROTOCOL_ERROR.getCode());
      throw new KitException(Code.PROTOCOL_ERROR,
        "ReqContext error, request context not allowed to be empty");
    } else {
      if (reqRequest.getContext().getUser() == null) {
        log.error("error code : [{}], ReqUser error, ReqUser not allowed to be empty",
          Code.PROTOCOL_ERROR.getCode());
        throw new KitException(Code.PROTOCOL_ERROR,
          "ReqUser error, ReqUser not allowed to be empty");
      }
      if (reqRequest.getContext().getDevice() == null) {
        log.error("error code : [{}], ReqDevice error, ReqDevice not allowed to be empty",
          Code.PROTOCOL_ERROR.getCode());
        throw new KitException(Code.PROTOCOL_ERROR,
          "ReqDevice error, ReqDevice not allowed to be empty");
      }
      if (reqRequest.getContext().getDevice().getBasic() == null) {
        log.error("error code : [{}], ReqBasic error, ReqBasic not allowed to be empty",
          Code.PROTOCOL_ERROR.getCode());
        throw new KitException(Code.PROTOCOL_ERROR,
          "ReqBasic error, ReqBasic not allowed to be empty");
      }
    }
    String deviceType = reqRequest.getContext().getDevice().getBasic().getDeviceType();
    String deviceId = reqRequest.getContext().getDevice().getBasic().getDeviceId();
    String masterId = reqRequest.getContext().getDevice().getBasic().getMasterId();
    String userId = reqRequest.getContext().getUser().getUserId();

    if (StringUtils.isBlank(deviceType)) {
      deviceType = DigestUtils.md5DigestAsHex((deviceId + userId).getBytes()).toUpperCase();
      reqRequest.getContext().getDevice().getBasic().setDeviceId(deviceType);
      log.warn("no deviceType put it like: {}", deviceType);
    }

    if (StringUtils.isBlank(masterId)) {
      masterId = DigestUtils.md5DigestAsHex((userId + deviceId).getBytes()).toUpperCase();
      reqRequest.getContext().getDevice().getBasic().setMasterId(masterId);
      log.warn("no masterId put it like: {}", masterId);
    }

    String sentence = reqRequest.getRequest().getContent().getSentence();
    String actionName = reqBasicInfo.getActionName();
    MDC.put(Const.NLP_INFO, String.join("_", deviceId, sentence, actionName));
    // 放置请求内容
    request.setAttribute(ReqAttrName.REQ_REQUEST, reqRequest);
  }

  private String getBodyFromRequest(HttpServletRequest request) throws IOException {
    try (BufferedReader reader = request.getReader()) {
      return reader.lines().map(String::trim).filter(s -> !s.isEmpty())
        .collect(Collectors.joining());

    }
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

  private String getUrl(HttpServletRequest request) {
    return request.getScheme() + "://" +
      request.getServerName() +
      ("http".equals(request.getScheme()) && request.getServerPort() == 80
        || "https".equals(request.getScheme()) && request.getServerPort() == 443 ? ""
        : ":" + request.getServerPort()) +
      request.getRequestURI() +
      (request.getQueryString() != null ? "?" + request.getQueryString() : "");
  }
}
