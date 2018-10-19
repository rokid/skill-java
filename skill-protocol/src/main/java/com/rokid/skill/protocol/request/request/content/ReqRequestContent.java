package com.rokid.skill.protocol.request.request.content;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rokid.skill.protocol.request.request.content.extra.ReqEventExtra;
import com.rokid.skill.protocol.request.request.content.slot.Slot;
import java.util.Map;
import lombok.Data;


/**
 * nlp相关信息
 *
 * @author wuyukai
 * @date 22/07/2018
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRequestContent {

    /**
     * IntentRequest,CloudApp 对应的 nlp intent
     *
     * @see com.rokid.skill.protocol.request.request.content.enums.RokidIntentEnums
     */
    private String intent;

    /**
     * asr 语音内容
     */
    private String sentence;

    /**
     * 词表
     */
    private Map<String, Slot> slots;

    /**
     * EventRequest,事件类型
     *
     * @see com.rokid.skill.protocol.request.request.content.enums.RokidEventEnums
     */
    private String event;

    /**
     * 事件透传数据
     */
    private ReqEventExtra extra;

}
