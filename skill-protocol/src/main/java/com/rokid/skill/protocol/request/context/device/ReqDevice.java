package com.rokid.skill.protocol.request.context.device;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rokid.skill.protocol.request.context.device.basic.ReqBasic;
import com.rokid.skill.protocol.request.context.device.devicemode.ReqDeviceMode;
import com.rokid.skill.protocol.request.context.device.location.ReqLocation;
import com.rokid.skill.protocol.request.context.device.media.ReqMedia;
import com.rokid.skill.protocol.request.context.device.screen.ReqScreen;
import lombok.Data;

/**
 * @author Bassam
 * @date 15/03/2017
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqDevice {

    /**
     * 展示了当前设备的基础信息，主要包含设备制造信息、时间信息、国家文字信息。
     */
    private ReqBasic basic;
    /**
     * 存放speech那边传递过来的linkage节点，以及儿童模式
     */
    private ReqDeviceMode deviceMode;
    /**
     * 展示了当前设备的屏幕信息，主要包含屏幕的分辨率信息。
     */
    private ReqScreen screen;
    /**
     * 向CloudApp表明当前设备上CloudAppClient中的MediaPlayer的状态信息。
     */
    private ReqMedia media;
    /**
     * 向CloudApp表明当前设备上CloudAppClient中的Voice的状态信息。
     */
    private ReqMedia voice;
    /**
     * 位置信息，现在只有一个经纬度
     */
    private ReqLocation location;

}
