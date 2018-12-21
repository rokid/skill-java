package com.rokid.skill.kit.service;

import com.rokid.skill.kit.bean.UserRecord;

/**
 * 用户记录服务，需要Skill自行实现才能满足DB的记录要求.
 *
 * @author wuyukai on 2018/7/21.
 */
public interface UserRecordService {

  /**
   * 记录服务日志.
   *
   * @param userRecord 用户对象
   * @return 记录状态
   */
  boolean record(UserRecord userRecord);

}
