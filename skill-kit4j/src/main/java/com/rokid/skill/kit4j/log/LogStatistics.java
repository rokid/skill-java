package com.rokid.skill.kit4j.log;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuyukai
 * @date 2018/8/8
 */

public class LogStatistics {

  private static ThreadLocal<ServiceLog> serviceContext = new ThreadLocal<>();

  public static void addExtra(String key, String value) {
    ServiceLog serviceLog = getServiceLog();
    Map<String, String> serviceExtra = serviceLog.getExtra();
    if (serviceExtra == null) {
      serviceExtra = new HashMap<>(4);
    }
    serviceExtra.put(key, value);
    serviceLog.setExtra(serviceExtra);
    serviceContext.set(serviceLog);

  }

  public static void addExtra(Map<String, String> extra) {
    ServiceLog serviceLog = getServiceLog();
    serviceLog.setExtra(extra);
    serviceContext.set(serviceLog);
  }

  public static void removeContext() {
    serviceContext.remove();
  }

  static ServiceLog getServiceLog() {
    ServiceLog serviceLog = serviceContext.get();
    if (serviceLog == null) {
      serviceLog = new ServiceLog();
      serviceContext.set(serviceLog);
    }
    return serviceLog;
  }

}
