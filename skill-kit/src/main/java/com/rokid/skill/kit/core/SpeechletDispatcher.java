package com.rokid.skill.kit.core;

import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.response.ResResponse;

/**
 * 所有请求事件的统一入口.
 *
 * @author Bassam
 */
public interface SpeechletDispatcher {

  /**
   * 业务分发.
   *
   * @param reqRequest 原始请求
   * @return 响应
   * @throws Exception 异常
   */
  ResResponse handle(ReqRequest reqRequest) throws Exception;
}
