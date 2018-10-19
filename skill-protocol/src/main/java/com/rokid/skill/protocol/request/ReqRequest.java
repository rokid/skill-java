package com.rokid.skill.protocol.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rokid.skill.protocol.request.context.ReqContext;
import com.rokid.skill.protocol.request.request.ReqRequestValue;
import com.rokid.skill.protocol.request.session.ReqSession;
import lombok.Data;


/**
 * CloudApp协议，Request对象 Request主要包含四块内容
 * 1、version：CloudApp协议版本；
 * 2、session：当前的会话信息；
 * 3、context：设备、用户、以及其他状态信息；
 * 4、request：业务请求内容
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqRequest {

    /**
     * 协议版本号，当前版本2.0.0
     */
    private String version;
    /**
     * 会话的信息
     */
    private ReqSession session;
    /**
     * 当前的设备信息，用户信息和应用状态
     */
    private ReqContext context;
    /**
     * 请求值，nlp相关信息
     */
    private ReqRequestValue request;
}
