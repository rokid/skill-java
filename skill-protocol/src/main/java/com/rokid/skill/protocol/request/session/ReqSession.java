package com.rokid.skill.protocol.request.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Map;
import lombok.Data;

/**
 * @author Bassam
 * @date 15/03/2017
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqSession {

    /**
     * 每次会话的唯一ID，由系统填充
     */
    private String sessionId;
    /**
     * 是否是新会话
     */
    private boolean newSession;
    /**
     * 为CloudApp提供attributes字段留保存上下文信息的字段
     */
    private Map<String, Object> attributes;

}
