package com.rokid.skill.demo.common;

import com.rokid.skill.kit4j.core.Code;
import com.rokid.skill.kit4j.exception.SkillException;
import com.rokid.skill.protocol.exception.ProtocolException;
import com.rokid.skill.protocol.response.ResResponse;
import com.rokid.skill.protocol.utils.ResponseBuilder;
import com.rokid.skill.protocol.utils.ResponseUtils;
import java.util.List;

/**
 * @author wuyukai
 */
public class ProtocolUtils {

  /**
   * 忽略事件，比如Voice.STARTED的EventRequest来的时候我们可以使用这个操作忽略这个请求
   */
  public static ResResponse ignore() {
    return ResponseUtils.buildIngoreEventResponse();
  }

  /**
   * 退出
   *
   * 举例: 若琪停止播放
   */
  public static ResResponse exit() {
    try {
      return ResponseUtils.buildExitNowResponse();
    } catch (ProtocolException e) {
      return null;
    }
  }

  /**
   * 从0开始播放音乐，并且停止TTS
   */
  public static ResResponse playSongAndFinished(String songId, String songUrl) {
    try {
      return ResponseBuilder.build().voiceStop().audioPlay(songId, null, songUrl, 0)
        .mediaEventDisable()
        .afterFinish()
        .create();
    } catch (ProtocolException e) {
      throw new SkillException(Code.PROTOCOL_ERROR, "协议异常", e.getCause());
    }
  }

  /**
   * 暂停音乐
   *
   * 举例: 若琪暂停播放
   */
  public static ResResponse pauseMusic() {
    try {
      return ResponseBuilder.build().mediaPause().mediaEventDisable().create();
    } catch (ProtocolException e) {
      throw new SkillException(Code.PROTOCOL_ERROR, "协议异常", e.getCause());
    }
  }

  /**
   * 播放完tts退出示例
   */
  public static ResResponse playTts(String content) {
    try {
      return ResponseBuilder.build().voicePlay(null, content).voiceEventDisable().create();
    } catch (ProtocolException e) {
      throw new SkillException(Code.PROTOCOL_ERROR, "协议异常", e.getCause());
    }
  }

  public static ResResponse confirm(String content, String confirmIntent,
    String confirmSlot, List<String> optionWords) {
    try {
      return ResponseBuilder.build().voicePlay(null, content).voiceEventDisable()
        .confirmOpen(confirmIntent, confirmSlot, 2, optionWords).create();
    } catch (ProtocolException e) {
      throw new SkillException(Code.PROTOCOL_ERROR, "协议异常", e.getCause());
    }
  }


  /**
   * 进入六十秒拾音状态
   */
  public static ResResponse pickUp(String content) {
    try {
      return ResponseBuilder.build().voicePlay(null, content).voiceEventDisable().pickupOpen(2)
        .sendChatCard(content).create();
    } catch (ProtocolException e) {
      throw new SkillException(Code.PROTOCOL_ERROR, "协议异常", e.getCause());
    }
  }

  /**
   * 继续播放音乐
   *
   * 举例: 若琪继续播放音乐
   */
  public static ResResponse resumeMusic() {
    try {
      return ResponseBuilder.build().mediaResume().mediaEventDisable().afterFinish().create();
    } catch (ProtocolException e) {
      throw new SkillException(Code.PROTOCOL_ERROR, "协议异常", e.getCause());
    }
  }
}
