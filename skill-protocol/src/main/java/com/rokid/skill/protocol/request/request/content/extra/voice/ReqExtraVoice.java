package com.rokid.skill.protocol.request.request.content.extra.voice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * voice 事件透传数据
 *
 * @author wuyukai
 * @date 22/07/2018
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqExtraVoice {

    /**
     * 当前播放数据的id，可用于透传字段作为唯一的标识，进行逻辑的串联
     */
    private String itemId;

}
