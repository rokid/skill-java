package com.rokid.skill.kit.exception;

import lombok.ToString;

/**
 * 响应码枚举，默认提供了一定的错误定义.
 *
 * @author wuyukai
 * @date 2018/7/22
 */

@ToString
public enum SkillKitCode implements SkillCode {

  /**
   * 没有找到.
   */
  NOT_FOUND(10000, "NotFound"),
  /**
   * 数据库错误.
   */
  DATABASE(10001, "DatabaseError"),
  /**
   * Redis错误.
   */
  REDIS(10002, "RedisError"),
  /**
   * Redis数据没有找到.
   */
  REDIS_FOUND(10003, "RedisNotFound"),
  /**
   * 安全检查错误.
   */
  SECURITY(10004, "SecurityError"),
  /**
   * 请求头有误.
   */
  INVALID_HEAD(10005, "InvalidHead"),
  /**
   * 请求数据有误.
   */
  INVALID_BODY(10006, "InvalidBody"),
  /**
   * 无效的参数.
   */
  INVALID_ARGUMENT(10007, "InvalidArgument"),
  /**
   * 响应错误.
   */
  RESPONSE(10008, "ResponseError"),
  /**
   * 参数缺失.
   */
  MISSING_ARGUMENT(10009, "MissingArgument"),
  /**
   * 方法不支持.
   */
  METHOD_NOT_ALLOWED(10010, "MethodNotAllowed"),

  /**
   * 内部错误.
   */
  INTERNAL(10011, "InternalError"),

  /**
   * 协议出错.
   */
  PROTOCOL_ERROR(10012, "protocol internal error"),
  /**
   * kit 包内部错误.
   */
  KIT_ERROR(10013, "kit internal error"),
  /**
   * 接入出错.
   */
  ACCESS(10014, "AccessDenied"),

  /**
   * 未知错误.
   */
  UNKNOWN(10015, "unknown error");

  private final int value;
  private final String hint;

  SkillKitCode(int value, String hint) {
    this.value = value;
    this.hint = hint;
  }

  @Override
  public int value() {
    return this.value;
  }

  @Override
  public String description() {
    return this.hint;
  }


}
