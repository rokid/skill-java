package com.rokid.skill.kit.exception;

import lombok.Getter;

/**
 * 通用异常情况.
 *
 * @author Bassam
 */
public class SkillKitException extends RuntimeException {

  private static final long serialVersionUID = -2586492750892355169L;

  @Getter
  private SkillCode skillCode;

  public SkillKitException(SkillCode skillCode) {
    super(skillCode.description());
  }

  public SkillKitException(SkillCode skillCode, String message) {
    super(message);
    this.skillCode = skillCode;
  }

  public SkillKitException(SkillCode skillCode, String message, Throwable cause) {
    super(message, cause);
    this.skillCode = skillCode;
  }

  public SkillKitException(SkillCode skillCode, Throwable cause) {
    super(cause);
    this.skillCode = skillCode;
  }

  protected SkillKitException(SkillCode skillCode, String message, Throwable cause,
      boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
    this.skillCode = skillCode;
  }
}
