package com.rokid.skill.kit.util;

import static com.rokid.skill.kit.constants.Const.TRACE_ID;

import com.rokid.skill.kit.bean.ReqBasicInfo;
import com.rokid.skill.kit.bean.ServiceLog;
import com.rokid.skill.kit.bean.SpeechletLog;
import com.rokid.skill.kit.bean.UserRecord;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.response.ResResponse;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 * 日志对象构造.
 *
 * @author wuyukai on 2018/4/4.
 */
@Slf4j
public class LogRecorderUtil {

  @Getter
  private static String ip;

  /**
   * 构建一个服务日志对象.
   */
  public static ServiceLog buildServiceLog(String requestBody, String result,
      String serviceName, String serviceVersion, Map<String, String> requestHeader, String status,
      String exception, String requestIp, String gmtCreate, Long costsTime) {
    ServiceLog sl = new ServiceLog();

    sl.setTraceId(MDC.get(TRACE_ID));
    sl.setServerIp(getIp());
    // 获取请求IP
    sl.setRequestIp(requestIp);
    sl.setServiceName(serviceName);
    sl.setServiceVersion(serviceVersion);
    sl.setMethodName("POST");
    sl.setRequestHeader(requestHeader);
    sl.setRequestBody(requestBody);

    sl.setStatus(status);
    sl.setResult(result);
    sl.setGmtCreate(gmtCreate);
    sl.setCostsTime(costsTime);
    sl.setException(exception);

    return sl;
  }

  /**
   * 构建一个语音请求的日志对象.
   */
  public static SpeechletLog buildSpeechletLog(ReqRequest reqRequest, ResResponse resResponse,
      String status, String gmtCreate, Long costsTime) {
    SpeechletLog mrl = new SpeechletLog();
    mrl.setRequestId(MDC.get(TRACE_ID));
    ReqBasicInfo reqBasicInfo = RequestUtil.getBasicInfo(reqRequest);
    mrl.setRequestVersion(reqRequest.getVersion());
    mrl.setSessionId(reqRequest.getSession().getSessionId());
    mrl.setMasterId(reqBasicInfo.getMasterId());
    mrl.setUserId(reqBasicInfo.getUserId());
    mrl.setDeviceId(reqBasicInfo.getDeviceId());
    mrl.setDeviceType(reqBasicInfo.getDeviceType());
    mrl.setDeviceVendor(reqBasicInfo.getDeviceVendor());
    mrl.setApplicationId(reqBasicInfo.getAppId());
    mrl.setActionType(reqBasicInfo.getActionType());
    mrl.setActionName(reqBasicInfo.getActionName());
    mrl.setSlots(RequestUtil.getSlots(reqRequest));
    mrl.setExtra(reqRequest.getRequest().getContent().getExtra());
    mrl.setStatus(status);
    mrl.setResResponse(resResponse);
    mrl.setGmtCreate(gmtCreate);
    mrl.setCostsTime(costsTime);
    return mrl;
  }

  /**
   * 构建一个用户记录.
   */
  public static UserRecord buildUserRecord(ReqRequest reqRequest) {
    UserRecord userRecord = new UserRecord();

    ReqBasicInfo reqBasicInfo = RequestUtil.getBasicInfo(reqRequest);
    userRecord.setMasterId(reqBasicInfo.getMasterId());
    userRecord.setUserId(reqBasicInfo.getUserId());
    userRecord.setDeviceId(reqBasicInfo.getDeviceId());
    userRecord.setVoicePrintId(reqRequest.getContext().getVoicePrint().getVoicePrintId());
    userRecord.setDeviceTypeId(reqBasicInfo.getDeviceType());
    userRecord.setDeviceVendor(reqBasicInfo.getDeviceVendor());

    return userRecord;
  }

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
