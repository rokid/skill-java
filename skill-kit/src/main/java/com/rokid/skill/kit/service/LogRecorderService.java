package com.rokid.skill.kit.service;

import com.rokid.skill.kit.bean.ServiceLog;
import com.rokid.skill.kit.bean.SpeechletLog;

/**
 * 日志记录服务.
 *
 * @author wuyukai on 2018/7/21.
 */
public interface LogRecorderService {

  /**
   * 记录服务日志.
   *
   * @param serviceLog 服务日志
   */
  void recordServiceLog(ServiceLog serviceLog);

  /**
   * 记录语音请求日志.
   *
   * @param speechletLog 语音请求日志
   */
  void recordSpeechletLog(SpeechletLog speechletLog);
}
