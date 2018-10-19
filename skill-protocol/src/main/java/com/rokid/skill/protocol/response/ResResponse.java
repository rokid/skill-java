package com.rokid.skill.protocol.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rokid.skill.protocol.response.response.ResResponseContent;
import com.rokid.skill.protocol.response.session.ResSession;
import lombok.Data;

/**
 * @author wuyukai
 * @date 22/07/2018
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResResponse {

    /**
     * 协议版本
     */
    public static final String RES_VERSION_V2 = "2.0.0";
    /**
     * 表明了Response协议的版本，必须由 CloudApp 填充。当前协议版本是 2.0.0.
     */
    private String version;
    /**
     * 表示当前应用的session，与Request中的信息一致，CloudApp 可以在
     */
    private ResSession session;
    /**
     * attributes 里填充自己需要的上下文信息用于后面的请求。 返回给 CloudAppClient 的Response内容。包括了card 和
     * action两个部分。card会在之后的协议更新中作详细说明。
     */
    private ResResponseContent response;


}
