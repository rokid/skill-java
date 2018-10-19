package com.rokid.skill.kit4j.log;

/**
 * @author wuyukai on 2018/7/21.
 */
public interface LogService {

    /**
     * 是否执行
     *
     * @return true执行服务中的方法
     */
    boolean support();

    /**
     * 记录服务日志
     * @param serviceLog 服务日志
     */
    void recordServiceLog(ServiceLog serviceLog);

}
