package com.rokid.skill.protocol.request.context.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * @author Bassam
 * @date 15/03/2017
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqUser {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 第三方账户授权的access_token
     */
    private String accountLinkedId;

}
