package com.rokid.skill.kit.service.impl;

import com.rokid.skill.kit.bean.ServiceLog;
import com.rokid.skill.kit.bean.SpeechletLog;
import com.rokid.skill.kit.service.LogRecorderService;
import com.rokid.skill.kit.util.JacksonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

/**
 * 默认的日志记录器.
 * @author wuyukai
 * @date 2018/12/16
 */
public class DefaultLogRecorderServiceImpl implements LogRecorderService {

  private static final  Logger serviceLogger = LoggerFactory.getLogger(ServiceLog.class);
  private static final  Logger masterRequestLogger = LoggerFactory.getLogger(SpeechletLog.class);

  /**
   * 创建服务请求日志.
   *
   * @param serviceLog 服务日志
   */
  @Override
  @Async("logTaskExecutor")
  public void recordServiceLog(ServiceLog serviceLog) {
    serviceLogger.debug("{}", JacksonUtil.toJson(serviceLog));
  }

  /**
   * 创建语音请求日志.
   *
   * @param speechletLog 日志对象
   */
  @Override
  @Async("logTaskExecutor")
  public void recordSpeechletLog(SpeechletLog speechletLog) {
    masterRequestLogger.debug("{}", JacksonUtil.toJson(speechletLog));
  }
}
