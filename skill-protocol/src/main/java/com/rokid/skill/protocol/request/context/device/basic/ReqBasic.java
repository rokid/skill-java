package com.rokid.skill.protocol.request.context.device.basic;


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
public class ReqBasic {

    /**
     * 注册生产商ID
     */
    private String vendor;
    /**
     * 该生产商设定的设备型号
     */
    private String deviceType;
    /**
     * 该型号下的设备ID
     */
    private String deviceId;
    /**
     * 国家及语言，标准locale格式
     */
    private String locale;
    /**
     * 设备当前时间，unix timestamp
     */
    private String timestamp;
    /**
     * 设备主人的Id;
     */
    private String masterId;
    /**
     * 设备当前激活词
     */
    @JsonProperty("voicetrigger")
    private String voiceTrigger;

}
