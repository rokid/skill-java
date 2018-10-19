package com.rokid.skill.protocol.request.context;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rokid.skill.protocol.request.context.application.ReqApplication;
import com.rokid.skill.protocol.request.context.device.ReqDevice;
import com.rokid.skill.protocol.request.context.user.ReqUser;
import com.rokid.skill.protocol.request.context.voiceprint.ReqVoicePrint;
import lombok.Data;

/**
 * @author Bassam
 * @date 15/03/2017
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqContext {

    /**
     * 主要用于设备响应以及手机端相关的Card对应关系，该字段为非必传字段，可以为空或者不传
     */
    private String speechId;
    /**
     * ApplicationInfo对象，目前只有应用ID
     */
    private ReqApplication application;
    /**
     * 此次请求发生时当前设备信息的描述
     */
    private ReqDevice device;
    /**
     * 展示了与当前设备绑定的用户信息，通常是设备对应手机应用的账号
     */
    private ReqUser user;

    @JsonProperty("voiceprint")
    private ReqVoicePrint voicePrint;

}
