package com.rokid.skill.protocol.response.response.action.directive.voice.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * @author Bassam
 * @date 25/03/2017
 */

@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResVoiceItem {

    private String itemId;
    private String tts;

}
