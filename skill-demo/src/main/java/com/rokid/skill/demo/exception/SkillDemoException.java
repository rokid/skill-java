package com.rokid.skill.demo.exception;

import com.rokid.skill.kit.exception.SkillCode;
import com.rokid.skill.kit.exception.SkillException;

/**
 * @author wuyukai
 * @date 2018/12/21
 */
public class SkillDemoException extends SkillException {

  public SkillDemoException(SkillCode code) {
    super(code);
  }

  public SkillDemoException(SkillCode code, String message) {
    super(code, message);
  }

  public SkillDemoException(SkillCode code, String message, Object... argArray) {
    super(code, message, argArray);
  }

  public SkillDemoException(SkillCode code, String message, Throwable cause) {
    super(code, message, cause);
  }

  public SkillDemoException(SkillCode code, Throwable cause) {
    super(code, cause);
  }

  protected SkillDemoException(SkillCode code, String message, Throwable cause,
    boolean enableSuppression, boolean writableStackTrace) {
    super(code, message, cause, enableSuppression, writableStackTrace);
  }

  @Override
  public SkillCode getCode() {
    return super.getCode();
  }
}
