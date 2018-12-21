package com.rokid.skill.kit.exception;

/**
 * 异常错误码.
 *
 * @author wuyukai
 * @date 2018/8/8
 */
public interface SkillCode {

  /**
   * 错误码.
   *
   * @return 错误码字符
   */
  int value();

  /**
   * 错误信息.
   *
   * @return 错误信息
   */
  String description();

}
