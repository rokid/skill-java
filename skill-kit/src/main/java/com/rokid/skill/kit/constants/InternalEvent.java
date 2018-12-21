package com.rokid.skill.kit.constants;


/**
 * Event 宏定义常量.
 *
 * @author wuyukai on 2018/6/16.
 */
public final class InternalEvent {

  private InternalEvent() {
  }

  /**
   * tts结束事件.
   */
  public static final String VOICE_STARTED = "Voice.STARTED";
  /**
   * tts失败事件.
   */
  public static final String VOICE_FAILED = "Voice.FAILED";
  /**
   * tts完成事件.
   */
  public static final String VOICE_FINISHED = "Voice.FINISHED";

  /**
   * 媒体开始事件.
   */
  public static final String MEDIA_STARTED = "Media.STARTED";
  /**
   * 媒体失败事件.
   */
  public static final String MEDIA_FAILED = "Media.FAILED";
  /**
   * 媒体暂停事件.
   */
  public static final String MEDIA_PAUSED = "Media.PAUSED";
  /**
   * 媒体完成事件.
   */
  public static final String MEDIA_FINISHED = "Media.FINISHED";
  /**
   * 媒体超时事件.
   */
  public static final String MEDIA_TIMEOUT = "Media.TIMEOUT";

  /**
   * 技能idle状态事件 不应该处理，直接ignore.
   */
  public static final String SKILL_EXIT = "Skill.EXIT";
  /**
   * 技能挂起事件.
   */
  public static final String SKILL_SUSPEND = "Skill.SUSPEND";
  /**
   * 会话结束事件.
   */
  public static final String SESSION_ENDED = "Session.ENDED";
}
