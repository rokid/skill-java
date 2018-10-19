package com.rokid.skill.kit4j.util;


import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.request.context.application.media.ReqApplicationMedia;
import com.rokid.skill.protocol.request.context.application.voice.ReqApplicationVoice;
import com.rokid.skill.protocol.request.context.device.location.ReqLocation;
import com.rokid.skill.protocol.request.request.ReqRequestValue;
import com.rokid.skill.protocol.request.request.content.extra.media.ReqExtraMedia;
import com.rokid.skill.protocol.request.request.content.extra.voice.ReqExtraVoice;
import com.rokid.skill.protocol.request.request.content.slot.Slot;
import com.rokid.skill.protocol.utils.ReqBasicInfo;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求处理工具类
 *
 * @author Bassam
 */
public class RequestUtils {

  /**
   * 获得基本请求信息
   */
  public static ReqBasicInfo getBasicInfo(ReqRequest reqRequest) {
    ReqBasicInfo reqBasicInfo = new ReqBasicInfo();
    if (reqRequest != null) {
      reqBasicInfo.setProtocolVersion(reqRequest.getVersion());
      if (reqRequest.getSession() != null) {
        reqBasicInfo.setSessionId(reqRequest.getSession().getSessionId());
      }
      if (reqRequest.getContext() != null) {
        if (reqRequest.getContext().getApplication() != null) {
          reqBasicInfo
            .setAppId(reqRequest.getContext().getApplication().getApplicationId());
        }
        if (reqRequest.getContext().getDevice() != null
          && reqRequest.getContext().getDevice().getBasic() != null) {
          reqBasicInfo
            .setDeviceId(reqRequest.getContext().getDevice().getBasic().getDeviceId());
          reqBasicInfo.setDeviceType(
            reqRequest.getContext().getDevice().getBasic().getDeviceType());
          reqBasicInfo.setDeviceVendor(
            reqRequest.getContext().getDevice().getBasic().getVendor());
          reqBasicInfo.setTimestamp(
            reqRequest.getContext().getDevice().getBasic().getTimestamp());
          reqBasicInfo
            .setMasterId(reqRequest.getContext().getDevice().getBasic().getMasterId());
          reqBasicInfo
            .setLocale(reqRequest.getContext().getDevice().getBasic().getLocale());
          reqBasicInfo.setVoiceTrigger(
            reqRequest.getContext().getDevice().getBasic().getVoiceTrigger());
        }
        if (reqRequest.getContext().getUser() != null) {
          reqBasicInfo.setUserId(reqRequest.getContext().getUser().getUserId());
        }
      }
      if (reqRequest.getRequest() != null) {
        reqBasicInfo.setRequestId(reqRequest.getRequest().getReqId());
        reqBasicInfo.setActionType(reqRequest.getRequest().getReqType());
        if (reqRequest.getRequest().getContent() != null) {
          if (ReqRequestValue.REQ_TYPE_EVENT.equals(reqBasicInfo.getActionType())) {
            reqBasicInfo.setActionName(reqRequest.getRequest().getContent().getEvent());
          } else if (ReqRequestValue.REQ_TYPE_INTENT
            .equals(reqBasicInfo.getActionType())) {
            reqBasicInfo
              .setActionName(reqRequest.getRequest().getContent().getIntent());
          }
        }
      }
    }
    return reqBasicInfo;
  }

  /**
   * 获得Media状态信息
   */
  public static ReqApplicationMedia getMediaState(ReqRequest reqRequest) {
    ReqApplicationMedia reqApplicationMedia = null;
    if (reqRequest != null && reqRequest.getContext() != null
      && reqRequest.getContext().getApplication() != null) {
      reqApplicationMedia = reqRequest.getContext().getApplication().getMedia();
    }
    if (reqApplicationMedia == null) {
      reqApplicationMedia = new ReqApplicationMedia();
    }
    return reqApplicationMedia;
  }

  /**
   * 获得VoiceState
   */
  public static ReqApplicationVoice getVoiceState(ReqRequest reqRequest) {
    ReqApplicationVoice reqApplicationVoice = null;
    if (reqRequest != null && reqRequest.getContext() != null
      && reqRequest.getContext().getApplication() != null) {
      reqApplicationVoice = reqRequest.getContext().getApplication().getVoice();
    }
    if (reqApplicationVoice == null) {
      reqApplicationVoice = new ReqApplicationVoice();
    }
    return reqApplicationVoice;
  }

  /**
   * 获取位置信息
   */
  public static ReqLocation getLocation(ReqRequest reqRequest) {
    ReqLocation reqLocation = null;
    if (reqRequest != null && reqRequest.getContext() != null
      && reqRequest.getContext().getDevice() != null) {
      reqLocation = reqRequest.getContext().getDevice().getLocation();
    }
    if (reqLocation == null) {
      reqLocation = new ReqLocation();
    }
    return reqLocation;
  }

  /**
   * 获取Media拓展信息
   */
  public static ReqExtraMedia getMediaExtra(ReqRequest reqRequest) {
    ReqExtraMedia reqExtraMedia = null;
    if (reqRequest != null && reqRequest.getRequest() != null
      && reqRequest.getRequest().getContent() != null
      && reqRequest.getRequest().getContent().getExtra() != null) {
      reqExtraMedia = reqRequest.getRequest().getContent().getExtra().getMedia();
    }
    if (reqExtraMedia == null) {
      reqExtraMedia = new ReqExtraMedia();
    }
    return reqExtraMedia;
  }

  /**
   * 获取Voice拓展信息
   */
  public static ReqExtraVoice getVoiceExtra(ReqRequest reqRequest) {
    ReqExtraVoice reqExtraVoice = null;
    if (reqRequest != null && reqRequest.getRequest() != null
      && reqRequest.getRequest().getContent() != null
      && reqRequest.getRequest().getContent().getExtra() != null) {
      reqExtraVoice = reqRequest.getRequest().getContent().getExtra().getVoice();
    }
    if (reqExtraVoice == null) {
      reqExtraVoice = new ReqExtraVoice();
    }
    return reqExtraVoice;
  }

  /**
   * 获取属性信息
   */
  public static Map<String, Object> getAttributes(ReqRequest reqRequest) {
    Map<String, Object> attributes = null;
    if (reqRequest != null && reqRequest.getSession() != null
      && reqRequest.getSession().getAttributes() != null) {
      attributes = reqRequest.getSession().getAttributes();
    }
    if (attributes == null) {
      attributes = new HashMap<>(8);
    }
    return attributes;
  }

  /**
   * 获取Slots
   */
  public static Map<String, Slot> getSlots(ReqRequest reqRequest) {
    Map<String, Slot> slots = null;
    if (reqRequest != null && reqRequest.getRequest() != null
      && reqRequest.getRequest().getContent() != null) {
      slots = reqRequest.getRequest().getContent().getSlots();
    }
    if (slots == null) {
      slots = new HashMap<>(8);
    }
    return slots;
  }

  /**
   * 获取Intent Request的ASR结果
   */
  public static String getSentence(ReqRequest reqRequest) {
    String sentence = "";
    if (reqRequest != null && reqRequest.getRequest() != null
      && reqRequest.getRequest().getContent() != null) {
      sentence = reqRequest.getRequest().getContent().getSentence();
    }
    return sentence;
  }
}
