package com.rokid.skill.kit.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rokid.skill.protocol.request.request.content.extra.ReqEventExtra;
import com.rokid.skill.protocol.request.request.content.slot.Slot;
import com.rokid.skill.protocol.response.ResResponse;
import java.util.Map;
import lombok.Data;

/**
 * 兼容老服务日志，不关注日志打印服务可去除此类.
 *
 * @author wuyukai
 */
@Data
@JsonInclude(Include.NON_NULL)
public class SpeechletLog {

  private String requestVersion;// 请求的版本
  private String requestId;// 语音请求过来的内容Id
  private String sessionId;// Session的Id
  private String masterId;// 设备主人Id（Alexa没有这个字段）
  private String userId;// 用户ID
  private String deviceType;// 设备类型(ALexa没有这个字段)
  private String deviceId;// 设备Id
  private String deviceVendor;// 设备认证码（Alexa没有这个字段）
  private String applicationId;// 应用ID
  private String actionType;// 事件类型
  private String actionName;// 操作名称/事件名称
  private Map<String, Slot> slots;
  private ReqEventExtra extra;
  private String status;// 状态
  private ResResponse resResponse;
  private String gmtCreate;
  private Long costsTime;
}