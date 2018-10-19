package com.rokid.skill.protocol.request.context.device.devicemode.linkage;


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
public class ReqLinkage {

    /**
     * 当前是否在联动模式
     */
    private boolean trigger;

}
