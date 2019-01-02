package com.rokid.skill.kit;

import com.rokid.skill.kit.service.HealthCheckService;
import com.rokid.skill.kit.service.LogRecorderService;
import com.rokid.skill.kit.service.UserRecordService;
import com.rokid.skill.kit.service.impl.DefaultHealthCheckServiceImpl;
import com.rokid.skill.kit.service.impl.DefaultLogRecorderServiceImpl;
import com.rokid.skill.kit.service.impl.DefaultUserRecordServiceImpl;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 技能配置服务.
 *
 * @author wuyukai
 * @date 2018/3/31 该类会被业务层的工程扫描用于加载上下文
 */

@EnableAsync
@Configuration
@ComponentScan
public class SkillContextConfig {

  @Bean
  @ConditionalOnMissingBean(value = LogRecorderService.class)
  public LogRecorderService logRecorderService() {
    return new DefaultLogRecorderServiceImpl();
  }

  @Bean
  @ConditionalOnMissingBean(value = UserRecordService.class)
  public UserRecordService userRecordService() {
    return new DefaultUserRecordServiceImpl();
  }

  @Bean
  @ConditionalOnMissingBean(value = HealthCheckService.class)
  public HealthCheckService healthCheckService() {
    return new DefaultHealthCheckServiceImpl();
  }

  /**
   * 异步线程注入.
   */
  @Bean("logTaskExecutor")
  @ConditionalOnMissingBean(name = "logTaskExecutor")
  public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(2);
    executor.setMaxPoolSize(4);
    executor.setQueueCapacity(100);
    executor.setKeepAliveSeconds(60);
    executor.setThreadNamePrefix("logTaskExecutor-");
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    return executor;
  }
}
