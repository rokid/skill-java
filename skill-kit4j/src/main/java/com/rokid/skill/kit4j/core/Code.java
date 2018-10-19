package com.rokid.skill.kit4j.core;

/**
 * 响应码枚举，默认提供了一定的错误定义
 *
 * @author wuyukai
 * @date 2018/7/22
 */

public enum Code implements EnumCodeName {

  /**
   * 协议出错
   */
  PROTOCOL_ERROR(10000, "protocol internal error"),
  /**
   * kit 包内部错误
   */
  KIT_ERROR(100001, "kit internal error"),

  /**
   * 接入出错
   */
  ACCESS(10002, "AccessDenied");

  private final int value;
  private final String name;

  Code(int value, String name) {
    this.value = value;
    this.name = name;
  }


  @Override
  public int getCode() {
    return this.value;
  }

  @Override
  public String getName() {
    return this.name;
  }
}
