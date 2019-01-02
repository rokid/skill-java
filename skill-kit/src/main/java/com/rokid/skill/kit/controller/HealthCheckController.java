package com.rokid.skill.kit.controller;

import com.rokid.skill.kit.constants.ServiceStatus;
import com.rokid.skill.kit.service.HealthCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 健康检查服务入口.
 *
 * @author wuyukai
 * @date 2018/12/6
 */
@Slf4j
@RestController
public class HealthCheckController {

  private final HealthCheckService healthCheckService;

  @Autowired
  public HealthCheckController(HealthCheckService healthCheckService) {
    this.healthCheckService = healthCheckService;
  }

  /**
   * 健康检查服务.
   * @return
   */
  @GetMapping("/check/health")
  public ServiceStatus checkHealth() {

    try {
      return healthCheckService.checkHealth();
    } catch (Exception e) {
      log.error("check health happen an error", e);
      return ServiceStatus.OFF;
    }
  }
}
