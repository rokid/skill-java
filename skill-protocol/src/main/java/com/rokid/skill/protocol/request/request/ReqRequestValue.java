package com.rokid.skill.protocol.request.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rokid.skill.protocol.request.request.content.ReqRequestContent;
import lombok.Data;

/**
 * @author Bassam
 * @date 15/03/2017
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRequestValue {

    public static final String REQ_TYPE_INTENT = "INTENT";
    public static final String REQ_TYPE_EVENT = "EVENT";

    /**
     * 表明请求的类型： INTENT 和 EVENT 分别对应 IntentRequest 和 EventRequest
     */
    private String reqType;

    /**
     * 每次请求都会对应一个唯一ID用以区分每一次的请求。请求ID将会与返回ID一一对应。
     */
    private String reqId;

    /**
     * IntentRequest 或 EventRequest的对象
     */
    private ReqRequestContent content;
}
