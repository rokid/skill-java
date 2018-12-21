package com.rokid.skill.kit.core;

import com.rokid.skill.protocol.request.ReqRequest;

/**
 * Skill请求的拦截服务.
 *
 * @author wuyukai on 2018/7/6.
 */
public interface SkillInterceptor {

  /**
   * 是否支持干预.
   *
   * @param reqRequest 请求
   * @return 为true表示支持干预，反之则表示不支持干预
   */
  boolean support(ReqRequest reqRequest);

  /**
   * 请求处理前进行干预，可干预请求中的所有参数.
   *
   * @param reqRequest 请求
   */
  void before(ReqRequest reqRequest);

  /**
   * 后置操作.
   *
   * @param reqRequest 请求 请求处理后进行干预
   */
  void after(ReqRequest reqRequest);

}
