package com.rokid.skill.kit.core;

import static com.rokid.skill.kit.constants.Const.TRACE_ID;
import static com.rokid.skill.kit.constants.Const.UNKNOWN_DEVICE_ID;
import static com.rokid.skill.kit.constants.Const.UNKNOWN_DEVICE_TYPE_ID;

import com.rokid.skill.kit.bean.ReqBasicInfo;
import com.rokid.skill.kit.constants.Const;
import com.rokid.skill.kit.exception.SkillKitCode;
import com.rokid.skill.kit.exception.SkillKitException;
import com.rokid.skill.kit.util.RequestUtil;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.request.request.content.enums.RokidActionTypeEnums;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.util.DigestUtils;

/**
 * 前置拦截器.
 *
 * @author wuyukai
 * @date 2018/12/16
 */
@Slf4j
public class PreInterceptor implements SkillInterceptor {

  @Override
  public boolean support(ReqRequest reqRequest) {
    return true;
  }

  @Override
  public void before(ReqRequest reqRequest) {

    if (reqRequest.getContext() == null) {
      log
          .error("error code : [{}], reqContext not allowed to be empty",
              SkillKitCode.PROTOCOL_ERROR.value());
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR,
          "reqContext not allowed to be empty");
    }

    // 基本请求信息
    ReqBasicInfo reqBasicInfo = RequestUtil.getBasicInfo(reqRequest);
    String requestId = reqBasicInfo.getRequestId();
    if (StringUtils.isBlank(requestId)) {
      requestId = UUID.randomUUID().toString().replace("-", "").toUpperCase();
      log.warn("no requestId, replace it by new uuid : {}", requestId);
      reqRequest.getRequest().setReqId(requestId);
      reqBasicInfo.setRequestId(requestId);
    }
    MDC.put(TRACE_ID, requestId);
    if (StringUtils.isBlank(reqBasicInfo.getActionType())
        || RokidActionTypeEnums.convert(reqBasicInfo.getActionType()) == null || StringUtils
        .isBlank(reqBasicInfo.getActionName())) {
      log.error("ReqBasicInfo error, action type is not allowed or action description is empty");
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR,
          "ReqBasicInfo error, action type is not allowed or action description is empty");
    }
    if (reqRequest.getRequest() == null) {
      log.error("ReqRequestValue error, request value not allowed to be empty");
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR,
          "ReqRequestValue error, request value not allowed to be empty");
    }
    if (reqRequest.getContext() == null) {
      log.error("error value : [{}], ReqContext error, request context not allowed to be empty",
          SkillKitCode.PROTOCOL_ERROR.value());
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR,
          "ReqContext error, request context not allowed to be empty");
    } else {
      if (reqRequest.getContext().getUser() == null) {
        log.error("error value : [{}], ReqUser error, ReqUser not allowed to be empty",
            SkillKitCode.PROTOCOL_ERROR.value());
        throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR,
            "ReqUser error, ReqUser not allowed to be empty");
      }
      if (reqRequest.getContext().getDevice() == null) {
        log.error("error value : [{}], ReqDevice error, ReqDevice not allowed to be empty",
            SkillKitCode.PROTOCOL_ERROR.value());
        throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR,
            "ReqDevice error, ReqDevice not allowed to be empty");
      }
      if (reqRequest.getContext().getDevice().getBasic() == null) {
        log.error("error value : [{}], ReqBasic error, ReqBasic not allowed to be empty",
            SkillKitCode.PROTOCOL_ERROR.value());
        throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR,
            "ReqBasic error, ReqBasic not allowed to be empty");
      }
    }
    String deviceType = reqRequest.getContext().getDevice().getBasic().getDeviceType();
    String deviceId = reqRequest.getContext().getDevice().getBasic().getDeviceId();
    String masterId = reqRequest.getContext().getDevice().getBasic().getMasterId();

    if (StringUtils.isBlank(deviceType)) {
      log.warn("no deviceType put it like: {}", UNKNOWN_DEVICE_TYPE_ID);
      reqRequest.getContext().getDevice().getBasic().setDeviceType(UNKNOWN_DEVICE_TYPE_ID);
    }
    if (StringUtils.isBlank(deviceId)) {
      log.warn("no deviceId put it like: {}", UNKNOWN_DEVICE_ID);
      reqRequest.getContext().getDevice().getBasic().setDeviceId(UNKNOWN_DEVICE_ID);
    }

    if (StringUtils.isBlank(masterId)) {
      masterId = DigestUtils.md5DigestAsHex((deviceType + deviceId).getBytes()).toUpperCase();
      log.warn("no masterId put it like: {}", masterId);
      reqRequest.getContext().getDevice().getBasic().setMasterId(masterId);
    }

    String sentence = reqRequest.getRequest().getContent().getSentence();
    String actionName = reqBasicInfo.getActionName();
    MDC.put(Const.NLP_INFO, String.join("_", deviceId, sentence, actionName));
  }

  @Override
  public void after(ReqRequest reqRequest) {
  }
}
