package com.rokid.skill.kit.exception;

import lombok.Getter;
import org.slf4j.helpers.MessageFormatter;

/**
 * 通用异常情况.
 *
 * @author Bassam
 */
public class SkillException extends RuntimeException {

  private static final long serialVersionUID = -2825045621799776069L;
  @Getter
  private SkillCode code;

  public SkillException(SkillCode code) {
    super(code.description());
    this.code = code;
  }

  public SkillException(SkillCode code, String message) {
    super(message);
    this.code = code;
  }

  /**
   * 异常构造方法.
   */
  public SkillException(SkillCode code, String message, Object... argArray) {

    //使用 sl4j 的写法格式化 message
    super(MessageFormatter.arrayFormat(message, argArray).getMessage());
    this.code = code;
  }

  public SkillException(SkillCode code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
  }

  public SkillException(SkillCode code, Throwable cause) {
    super(cause);
    this.code = code;
  }

  protected SkillException(SkillCode code, String message, Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.code = code;
  }
}
