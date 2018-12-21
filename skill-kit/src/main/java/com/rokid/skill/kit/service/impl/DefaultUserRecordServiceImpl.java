package com.rokid.skill.kit.service.impl;

import com.rokid.skill.kit.bean.UserRecord;
import com.rokid.skill.kit.service.UserRecordService;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认的用户请求记录服务.
 * @author wuyukai
 * @date 2018/12/18
 */
@Slf4j
public class DefaultUserRecordServiceImpl implements UserRecordService {

  @Override
  public boolean record(UserRecord userRecord) {
    log.info("userRecord : {}", userRecord);
    return true;
  }
}
