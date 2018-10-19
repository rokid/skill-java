package com.rokid.skill.protocol.response.response.card.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rokid.skill.protocol.response.response.card.ResCard;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResChatCard extends ResCard {

    private static final String CARD_TYPE = "chat";

    public ResChatCard() {
        setType(CARD_TYPE);
    }

    public ResChatCard(String tts) {
        setType(CARD_TYPE);
        setContent(getTTSContent(tts));
    }

    @Getter
    @Setter
    private String content;

    private String getTTSContent(String tts) {
        return tts.replaceAll("<[^>]*>", "").replaceAll("\\[[^]]*]", "");
    }

}
