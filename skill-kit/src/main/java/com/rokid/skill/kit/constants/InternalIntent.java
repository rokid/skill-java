package com.rokid.skill.kit.constants;

/**
 * 系统级Intent枚举.
 *
 * @author wuyukai
 * @date 2018/7/31
 */
public final class InternalIntent {

  private InternalIntent() {
  }

  /**
   * 技能打开意图.
   */
  public static final String WELCOME_INTENT = "ROKID.INTENT.WELCOME";
  /**
   * 未知意图.
   */
  public static final String UNKNOWN_INTENT = "ROKID.INTENT.UNKNOWN";
  /**
   * 技能退出意图.
   */
  public static final String EXIT_INTENT = "ROKID.INTENT.EXIT";

  public static final String LOOPOFF_INTENT = "ROKID.INTENT.LOOPOFF";
  public static final String LOOPON_INTENT = "ROKID.INTENT.LOOPON";
  public static final String SHUFFLEOFF_INTENT = "ROKID.INTENT.SHUFFLEOFF";
  public static final String SHUFFLEON_INTENT = "ROKID.INTENT.SHUFFLEON";
  public static final String REPEAT_INTENT = "ROKID.INTENT.REPEAT";
  public static final String STARTOVER_INTENT = "ROKID.INTENT.STARTOVER";
  public static final String STOP_INTENT = "ROKID.INTENT.STOP";
  public static final String PREVIOUS_INTENT = "ROKID.INTENT.PREVIOUS";
  public static final String NEXT_INTENT = "ROKID.INTENT.NEXT";
  public static final String PAUSE_INTENT = "ROKID.INTENT.PAUSE";
  public static final String RESUME_INTENT = "ROKID.INTENT.RESUME";
}
