package com.rokid.skill.protocol.response.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rokid.skill.protocol.response.response.action.ResAction;
import com.rokid.skill.protocol.response.response.card.ResCard;
import lombok.Data;

/**
 * @author Bassam
 * @date 15/03/2017
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResResponseContent {

    private ResCard card;
    /**
     * voice 和 media。voice 表示了语音交互的返回。media 是对媒体播放的返回。
     */
    private ResAction action;
}
