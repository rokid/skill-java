package com.rokid.skill.protocol.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author wuyukai
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqBasicInfo {

    /**
     * 当前协议版本
     */
    private String protocolVersion;
    /**
     * 请求ID
     */
    private String requestId;
    /**
     * SessionId
     */
    private String sessionId;
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 设备类型
     */
    private String deviceType;
    /**
     * 厂商ID
     */
    private String deviceVendor;
    /**
     * 设备主人的ID
     */
    private String masterId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户语言
     */
    private String locale;
    /**
     * 操作类型
     */
    private String actionType;
    /**
     * 操作名称
     */
    private String actionName;
    /**
     * 设备时间戳
     */
    private String timestamp;
    /**
     * 设备当前激活词
     */
    @JsonProperty("voicetrigger")
    private String voiceTrigger;
}
