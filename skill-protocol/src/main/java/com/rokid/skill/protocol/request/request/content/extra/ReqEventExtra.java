package com.rokid.skill.protocol.request.request.content.extra;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rokid.skill.protocol.request.request.content.extra.media.ReqExtraMedia;
import com.rokid.skill.protocol.request.request.content.extra.voice.ReqExtraVoice;
import lombok.Data;

/**
 * 事件透传数据
 *
 * @author wuyukai
 * @date 22/07/2018
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqEventExtra {

    /**
     * 媒体透传数据
     */
    private ReqExtraMedia media;

    /**
     * tts透传数据
     */
    private ReqExtraVoice voice;
}
