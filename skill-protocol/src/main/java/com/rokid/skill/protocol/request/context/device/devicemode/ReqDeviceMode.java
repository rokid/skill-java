package com.rokid.skill.protocol.request.context.device.devicemode;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rokid.skill.protocol.request.context.device.devicemode.linkage.ReqLinkage;
import com.rokid.skill.protocol.request.context.device.devicemode.usermode.ReqUserMode;
import lombok.Data;

/**
 * @author kylehan
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqDeviceMode {


    private ReqLinkage linkage;

    private ReqUserMode userMode;

}
