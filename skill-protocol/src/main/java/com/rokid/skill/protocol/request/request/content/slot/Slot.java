package com.rokid.skill.protocol.request.request.content.slot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


/**
 * @author wuyukai
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Slot {

    /**
     * slot 类型
     */
    private String type;

    /**
     * slot 值，对应 nlp 配置中 outValue
     */
    private String value;

    /**
     * slot原始值，对应 nlp 配置中 value
     */
    private String origin;

    /**
     * utc 时间字段
     */
    private String utc;

    /**
     * 拼音
     */
    private String pinyin;

    /**
     * 模糊搜索原始值
     */
    @JsonProperty("vague_origin")
    private String vagueOrigin;
}
