package com.rokid.skill.protocol.request.context.device.devicemode.usermode;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * @author kylehan
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqUserMode {

    /**
     * 当前是否在儿童模式
     */
    private boolean childMode;

}
