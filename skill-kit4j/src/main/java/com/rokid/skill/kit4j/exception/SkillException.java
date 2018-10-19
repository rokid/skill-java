package com.rokid.skill.kit4j.exception;

import com.rokid.skill.kit4j.core.EnumCodeName;
import lombok.Getter;
import lombok.Setter;

/**
 * 语音业务异常情况
 *
 * @author wuyukai
 */
public class SkillException extends RuntimeException {

  private static final long serialVersionUID = 2006382839104348426L;

  @Getter
  @Setter
  private EnumCodeName code;
  @Getter
  @Setter
  private String actionType;
  @Getter
  @Setter
  private String actionName;

  public SkillException(EnumCodeName code) {
    super(code.getName());
    this.code = code;
  }

  public SkillException(EnumCodeName code, String msg) {
    super(msg);
    this.code = code;
  }


  public SkillException(EnumCodeName code, String msg, Throwable throwable) {
    super(msg, throwable);
    this.code = code;
  }

  public SkillException(EnumCodeName code, String msg, String actionType, String actionName) {
    super(msg);
    this.code = code;
    this.actionType = actionType;
    this.actionName = actionName;
  }

  public SkillException(EnumCodeName code, String msg, Throwable throwable, String actionType,
    String actionName) {
    super(msg, throwable);
    this.code = code;
    this.actionType = actionType;
    this.actionName = actionName;
  }
}
