package com.rokid.skill.demo.processor;

import com.rokid.skill.demo.common.ProtocolUtils;
import com.rokid.skill.kit4j.aop.Action;
import com.rokid.skill.kit4j.aop.Processor;
import com.rokid.skill.kit4j.constants.Event;
import com.rokid.skill.kit4j.constants.SystemIntent;
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
    @Action(event = Event.SKILL_EXIT)
    public ResResponse skillExit(ReqRequest reqRequest) {
        return ProtocolUtils.ignore();
    }

    @Action(event = SystemIntent.UNKNOWN_INTENT)
    public ResResponse unknown(ReqRequest reqRequest) {
        return ProtocolUtils.ignore();
    }

    @Action(event = Event.VOICE_STARTED)
    public ResResponse voiceStarted(ReqRequest reqRequest) {
        return ProtocolUtils.ignore();
    }

    @Action(event = Event.VOICE_FINISHED)
    public ResResponse voiceFinished(ReqRequest reqRequest) {
        return ProtocolUtils.ignore();
    }
}
