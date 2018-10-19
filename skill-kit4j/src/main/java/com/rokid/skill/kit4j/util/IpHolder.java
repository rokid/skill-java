package com.rokid.skill.kit4j.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 获得本机的Ip地址
 *
 * @author Bassam
 */
public class IpHolder {

  private IpHolder() {
  }

  @Getter
  private static String ip;

  static {
    ip = getSelfIp();
  }

  private static String getSelfIp() {
    try {
      Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface
        .getNetworkInterfaces();
      InetAddress ip;
      if (allNetInterfaces != null) {
        while (allNetInterfaces.hasMoreElements()) {
          NetworkInterface netInterface = allNetInterfaces.nextElement();
          Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
          while (addresses.hasMoreElements()) {
            ip = addresses.nextElement();
            if (ip instanceof Inet4Address) {
              if (!StringUtils.equals("127.0.0.1", ip.getHostAddress())) {
                return ip.getHostAddress();
              }
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
