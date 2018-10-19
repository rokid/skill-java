package com.rokid.skill.protocol.response.response.action.directive.display;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rokid.skill.protocol.response.response.action.directive.ResDirective;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * @author Bassam
 * @date 25/03/2017
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResDisplay extends ResDirective {

    public ResDisplay() {
        setType("display");
    }

    private String action;
    private boolean needEventCallback;
    private long duration;

}
