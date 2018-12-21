package com.rokid.skill.kit.exception;

import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.response.ResResponse;

/**
 * 异常分发.
 *
 * @author wuyukai
 * @date 2018/8/20
 */
public interface ExceptionDispatcher {

  /**
   * 异常处理.
   *
   * @param reqRequest 请求体
   * @param exception 异常
   * @return 响应
   */
  ResResponse handleException(ReqRequest reqRequest, SkillException exception);

  /**
   * 异常上报.
   *
   * @param reqRequest 请求体
   * @param exception 异常
   */
  void reportException(ReqRequest reqRequest, SkillException exception);

}
