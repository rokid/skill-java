package com.rokid.skill.protocol.request.context.voiceprint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author wuyukai
 * @date 2018/8/3
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqVoicePrint {

  /**
   * 当前声纹ID
   */
  @JsonProperty("voiceprintId")
  private String voicePrintId;

}
