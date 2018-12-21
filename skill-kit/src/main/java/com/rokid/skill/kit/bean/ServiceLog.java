package com.rokid.skill.kit.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Map;
import lombok.Data;

/**
 * 兼容老服务日志，不关注日志打印服务可去除此类.
 *
 * @author wuyukai
 */
@Data
@JsonInclude(Include.NON_NULL)
public class ServiceLog {

  private String traceId;// 语音请求ID
  private String requestIp;// 请求过来的用户IP
  private String serverIp;// 当前服务器的IP（用于判断是那台服务器处理了这个请求）
  private String serviceName;// 服务名称
  private String serviceVersion;// 系统服务版本
  private String methodName;// 方法名称
  private Map<String, String> requestHeader;// 请求头
  private String requestBody;// 请求体
  private String status;// 状态
  private String result;// 返回结果
  private String exception;// 异常记录信息
  private String gmtCreate;// 请求时间
  private Long costsTime;// 耗时
}