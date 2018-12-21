package com.rokid.skill.kit.util;

import com.rokid.skill.kit.exception.SkillKitCode;
import com.rokid.skill.kit.exception.SkillKitException;
import com.rokid.skill.protocol.exception.ProtocolException;
import com.rokid.skill.protocol.response.ResResponse;
import java.util.List;

/**
 * 协议处理工具类.
 *
 * @author wuyukai
 */
public class ProtocolUtil {

  public static ResResponse ignore() {
    return ResponseUtil.buildIngoreEventResponse();
  }

  /**
   * 播放一个退出.
   * @return
   */
  public static ResResponse exit() {
    try {
      return ResponseUtil.buildExitNowResponse();
    } catch (ProtocolException e) {
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR, e.getCause());
    }
  }

  /**
   * 播放一个音频，并且不需要接收状态变更事件.
   */
  public static ResResponse playAudio(String audioId, String audioUrl) {
    try {
      return ResponseUtil.ResponseBuilder.build().audioPlay(audioId, null, audioUrl, 0)
          .mediaEventDisable()
          .create();
    } catch (ProtocolException e) {
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR, e.getCause());
    }
  }

  /**
   * 播放一个音频并且需要接收该音频的状态变化事件.
   */
  public static ResResponse playAudioWithEvent(String audioId, String audioUrl) {
    try {
      return ResponseUtil.ResponseBuilder.build().audioPlay(audioId, null, audioUrl, 0).create();
    } catch (ProtocolException e) {
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR, e.getCause());
    }
  }

  /**
   * 从指定时间开始播放一个音频.
   */
  public static ResResponse playAudioWithOffset(String audioId, String audioUrl, long offset) {
    try {
      return ResponseUtil.ResponseBuilder.build().audioPlay(audioId, null, audioUrl, offset)
          .mediaEventDisable()
          .create();
    } catch (ProtocolException e) {
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR, e.getCause());
    }
  }

  /**
   * 从指定时间开始播放音频.
   */
  public static ResResponse playAudioWithOffsetAndEvent(String audioId, String audioUrl,
      long offset) {
    try {
      return ResponseUtil.ResponseBuilder.build().audioPlay(audioId, null, audioUrl, offset)
          .create();
    } catch (ProtocolException e) {
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR, e.getCause());
    }
  }

  /**
   * 暂停音频.
   */
  public static ResResponse pauseAudio() {
    try {
      return ResponseUtil.ResponseBuilder.build().mediaPause().mediaEventDisable().create();
    } catch (ProtocolException e) {
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR, e.getCause());
    }
  }

  /**
   * 暂停一个音频，并且需要事件.
   */
  public static ResResponse pauseAudioWithEvent() {
    try {
      return ResponseUtil.ResponseBuilder.build().mediaPause().create();
    } catch (ProtocolException e) {
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR, e.getCause());

    }
  }

  /**
   * 继续播放一个音频.
   */
  public static ResResponse resumeAudio() {
    try {
      return ResponseUtil.ResponseBuilder.build().mediaResume().mediaEventDisable().create();
    } catch (ProtocolException e) {
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR, e.getCause());
    }
  }

  /**
   * 继续播放音频，并且需要一个事件.
   */
  public static ResResponse resumeAudioWithEvent() {
    try {
      return ResponseUtil.ResponseBuilder.build().mediaResume().create();
    } catch (ProtocolException e) {
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR, e.getCause());
    }
  }

  /**
   * 播放完tts退出示例.
   */
  public static ResResponse playTts(String tts) {
    try {
      return ResponseUtil.ResponseBuilder.build().voicePlay(null, tts).voiceEventDisable()
          .sendChatCard(tts).create();
    } catch (ProtocolException e) {
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR, e.getCause());
    }
  }

  /**
   * 播放一个TTS，并且需要接受TTS状态变更的相关事件.
   */
  public static ResResponse playTtsWithEvent(String tts) {
    try {
      return ResponseUtil.ResponseBuilder.build().voicePlay(null, tts).sendChatCard(tts).create();
    } catch (ProtocolException e) {
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR, e.getCause());
    }
  }

  /**
   * 播放一个音频以及TTS.
   */
  public static ResResponse playAudioAndTts(String audioId, String audioUrl, String tts) {
    try {
      return ResponseUtil.ResponseBuilder.build().audioPlay(audioId, null, audioUrl, 0)
          .mediaNoWait().voiceNoWait()
          .voicePlay(null, tts).create();
    } catch (ProtocolException e) {
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR, e.getCause());
    }
  }

  /**
   * 播放一个音频以及TTS，并且不需要进行音频压制.
   */
  public static ResResponse playAudioAndTtsDisableSuppress(String audioId, String audioUrl,
      String tts) {
    try {
      return ResponseUtil.ResponseBuilder.build().audioPlay(audioId, null, audioUrl, 0)
          .mediaNoWait().disableSuppress()
          .voiceNoWait().voicePlay(null, tts).create();
    } catch (ProtocolException e) {
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR, e.getCause());
    }
  }

  /**
   * 创建一个Confirm的响应.
   */
  public static ResResponse confirm(String content, String confirmIntent,
      String confirmSlot, List<String> optionWords) {
    try {
      return ResponseUtil.ResponseBuilder.build().voicePlay(null, content).voiceEventDisable()
          .confirmOpen(confirmIntent, confirmSlot, 3, optionWords).sendChatCard(content).create();
    } catch (ProtocolException e) {
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR, e.getCause());
    }
  }

  /**
   * 进入六十秒拾音状态.
   */
  public static ResResponse pickUp(String content) {
    try {
      return ResponseUtil.ResponseBuilder.build().voicePlay(null, content).voiceEventDisable()
          .pickupOpen(1)
          .create();
    } catch (ProtocolException e) {
      throw new SkillKitException(SkillKitCode.PROTOCOL_ERROR, e.getCause());
    }
  }

  public static String getRealContent(String tts) {
    return tts.replaceAll("<[^>]*>", "").replaceAll("\\[[^]]*]", "");
  }

}
