package com.rokid.skill.kit.service.impl;

import com.rokid.skill.kit.constants.ServiceStatus;
import com.rokid.skill.kit.service.HealthCheckService;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认的健康检查服务.
 *
 * @author wuyukai
 * @date 2018/12/18
 */
@Slf4j
public class DefaultHealthCheckServiceImpl implements HealthCheckService {

  @Override
  public ServiceStatus checkHealth() {
    log.error(
        "not define a bean named HealthCheckService, "
            + "use the DefaultHealthCheckServiceImpl instead, and set service status as WARNING");
    return ServiceStatus.WARNING;
  }
}
