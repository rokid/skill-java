package com.rokid.skill.kit.service;

import com.rokid.skill.kit.constants.ServiceStatus;

/**
 * 健康检查服务.
 *
 * @author wuyukai
 * @date 2018/12/18
 */
public interface HealthCheckService {

  /**
   * 健康检查.
   *
   * @return 服务状态
   */
  ServiceStatus checkHealth();

}
