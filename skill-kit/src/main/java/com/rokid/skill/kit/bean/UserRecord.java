package com.rokid.skill.kit.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * <p>
 * 用户基本信息表.
 * </p>
 *
 * @author wuyukai
 * @since 2018-08-27
 */
@Data
@JsonInclude(Include.NON_NULL)
public class UserRecord {

  /**
   * 用户id.
   */
  private String userId;

  /**
   * 声纹 id.
   */
  private String voicePrintId;
  /**
   * master_id.
   */
  private String masterId;

  /**
   * 设备id.
   */
  private String deviceId;

  /**
   * 设备类型.
   */
  private String deviceTypeId;

  /**
   * 设备厂商.
   */
  private String deviceVendor;

}
