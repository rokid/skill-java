package com.rokid.skill.protocol.response.response.card.chat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rokid.skill.protocol.response.response.card.ResCard;
import lombok.ToString;

/**
 * @author wuyukai
 */
@ToString(callSuper = true)
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResAccountLinkCard extends ResCard {

    private static final String CARD_TYPE = "ACCOUNT_LINK";

    /**
     * login card
     */
    public ResAccountLinkCard() {
        setType(CARD_TYPE);
    }
}
