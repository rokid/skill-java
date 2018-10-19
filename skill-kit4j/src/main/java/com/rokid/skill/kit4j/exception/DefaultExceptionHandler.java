package com.rokid.skill.kit4j.exception;

import com.rokid.skill.kit4j.constants.ReqAttrName;
import com.rokid.skill.kit4j.log.LogFactory;
import com.rokid.skill.kit4j.log.LogService;
import com.rokid.skill.kit4j.log.LogStatistics;
import com.rokid.skill.kit4j.log.ServiceLog;
import com.rokid.skill.kit4j.util.JacksonUtil;
import com.rokid.skill.protocol.response.ResResponse;
import com.rokid.skill.protocol.utils.ResponseUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author wuyukai on 2018/6/16.
 */
@Slf4j
@Component
public class DefaultExceptionHandler {

  @Autowired(required = false)
  private LogService logService;

  public ResResponse errorHandler(HttpServletRequest request, HttpServletResponse response,
    Exception ex) {
    ResResponse resResponse = ResponseUtils.buildIngoreEventResponse();
    log.info("RESPONSE : {}", resResponse);
    response.setStatus(HttpStatus.OK.value());
    String result = JacksonUtil.toJson(resResponse);
    request.setAttribute(ReqAttrName.RESULT, result);
    request.setAttribute(ReqAttrName.EXCEPTION, ExceptionUtils.getStackTrace(ex));
    Long beginTime = (Long) request.getAttribute(ReqAttrName.REQ_BEGIN_TIME);
    Long costsTime = System.currentTimeMillis() - beginTime;
    request.setAttribute("costTime", costsTime);

    if (logService != null) {
      ServiceLog sl = LogFactory.buildServiceLog(request);
      logService.recordServiceLog(sl);
    }
    LogStatistics.removeContext();
    MDC.clear();
    return resResponse;
  }

}
