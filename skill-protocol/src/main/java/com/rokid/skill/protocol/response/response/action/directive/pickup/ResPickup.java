package com.rokid.skill.protocol.response.response.action.directive.pickup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rokid.skill.protocol.response.response.action.directive.ResDirective;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author wuyukai
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResPickup extends ResDirective {

  private boolean enable;
  private Long durationInMilliseconds;
  /**
   * 重试一次的TTS
   */
  private String retryTts;
  private Integer retryCount;

  public ResPickup() {
    setType("pickup");
  }

}
