package com.rokid.skill.kit4j.log;

import com.rokid.skill.kit4j.constants.Const;
import com.rokid.skill.kit4j.constants.ReqAttrName;
import com.rokid.skill.kit4j.util.IpHolder;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.request.request.content.ReqRequestContent;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wuyukai on 2018/4/4.
 */
@Slf4j
public class LogFactory {

  public static ServiceLog buildServiceLog(HttpServletRequest request) {
    ServiceLog sl = LogStatistics.getServiceLog();
    String id = UUID.randomUUID().toString().replace("-", "").toUpperCase();
    sl.setId(id);
    sl.setServerIp(IpHolder.getIp());
    ReqRequest reqRequest = (ReqRequest) request.getAttribute(ReqAttrName.REQ_REQUEST);
    sl.setSpeechletId(reqRequest.getRequest().getReqId());
    sl.setRequestId(reqRequest.getRequest().getReqId());
    sl.setMasterId(reqRequest.getContext().getDevice().getBasic().getMasterId());
    sl.setDeviceId(reqRequest.getContext().getDevice().getBasic().getDeviceId());
    sl.setDeviceType(reqRequest.getContext().getDevice().getBasic().getDeviceType());
    ReqRequestContent content = reqRequest.getRequest().getContent();
    String actionName = content.getIntent();
    if (StringUtils.isBlank(actionName)) {
      actionName = content.getEvent();
    }
    sl.setActionName(actionName);
    sl.setSessionId(reqRequest.getSession().getSessionId());
    sl.setAppCode(reqRequest.getContext().getApplication().getApplicationId());
    sl.setAppVersion(reqRequest.getVersion());
    // 获取请求IP
    String requestIp = Optional
      .ofNullable(request.getHeader(Const.HEADER_NAME_REAL_IP))
      .map(Object::toString).orElse("");
    sl.setRequestIp(requestIp);
    String serviceName = Optional.ofNullable(request
      .getAttribute(ReqAttrName.SERVICE_NAME)).map(Object::toString).orElse("");
    sl.setServiceName(serviceName);
    // 服务版本，正在处理日志的时候最好重载掉
    String serviceVersion = (String) request.getAttribute(ReqAttrName.SERVICE_VERSION);
    sl.setServiceVersion(serviceVersion);
    sl.setMethodName(request.getMethod());
    String requestHeader = Optional.ofNullable(request
      .getAttribute(ReqAttrName.REQ_HEADER)).map(Object::toString).orElse("");
    sl.setRequestHeader(requestHeader);
    String requestBody = Optional.ofNullable(request
      .getAttribute(ReqAttrName.REQ_BODY)).map(Object::toString).orElse("");
    sl.setRequestBody(requestBody);
    String status = (String) request.getAttribute(ReqAttrName.STATUS);
    sl.setStatus(status);
    String result = Optional.ofNullable(request.getAttribute(ReqAttrName.RESULT))
      .map(Object::toString).orElse("");
    sl.setResult(result);
    Long beginTime = Optional.ofNullable(request
      .getAttribute(ReqAttrName.REQ_BEGIN_TIME)).map(l -> (Long) l).orElse(0L);
    sl.setGmtCreated(beginTime);
    Long costTime = Optional.ofNullable(request.getAttribute(ReqAttrName.REQ_COST_TIME))
      .map(l -> (Long) l).orElse(0L);
    sl.setCostsTime(costTime);
    String exception = Optional.ofNullable(request
      .getAttribute(ReqAttrName.EXCEPTION)).map(Object::toString).orElse("");
    sl.setException(exception);
    return sl;
  }

}
