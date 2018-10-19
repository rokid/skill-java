package com.rokid.skill.protocol.response.response.action.directive.confirm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rokid.skill.protocol.response.response.action.directive.ResDirective;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 *
 * @author Bassam
 * @date 25/03/2017
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResConfirm extends ResDirective {

    public ResConfirm() {
        setType("confirm");
    }

    private String confirmIntent;
    private String confirmSlot;
    private int retryCount;
    private List<String> optionWords;
}
