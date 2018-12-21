package com.rokid.skill.kit.constants;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

/**
 * 服务状态码.
 *
 * @author wuyukai
 * @date 2018/12/18
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ServiceStatus {

  /**
   * 服务状态.
   */
  SERVING("SERVING"),
  WARNING("WARNING"),
  OFF("OFF");

  ServiceStatus(String status) {
    this.status = status;
  }

  @Getter
  private String status;

}
