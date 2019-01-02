package com.rokid.skill.demo.processor;

import com.rokid.skill.kit.annotation.Action;
import com.rokid.skill.kit.annotation.Processor;
import com.rokid.skill.kit.constants.InternalEvent;
import com.rokid.skill.kit.constants.InternalIntent;
import com.rokid.skill.kit.util.ProtocolUtil;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.response.ResResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author wuyukai
 * @date 2018/7/30
 */
@Slf4j
@Processor
public class EventProcessor {

  /**
   * 退出时间忽略，confirm可生效
   *
   * @return ignore响应
   */
  @Action(event = InternalEvent.SKILL_EXIT)
  public ResResponse skillExit(ReqRequest reqRequest) {
    return ProtocolUtil.ignore();
  }

  @Action(event = InternalIntent.UNKNOWN_INTENT)
  public ResResponse unknown(ReqRequest reqRequest) {
    return ProtocolUtil.ignore();
  }

  @Action(event = InternalEvent.VOICE_STARTED)
  public ResResponse voiceStarted(ReqRequest reqRequest) {
    return ProtocolUtil.ignore();
  }

  @Action(event = InternalEvent.VOICE_FINISHED)
  public ResResponse voiceFinished(ReqRequest reqRequest) {
    return ProtocolUtil.ignore();
  }
}
