package com.rokid.skill.protocol.request.request.content.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wuyukai
 * @date 2018/7/22
 */
public enum RokidActionTypeEnums {

    /**
     * 通过激活词打开应用时会发送这个Intent
     */
    EVENT_REQUEST("EVENT", "事件类型"),

    INTENT_REQUEST("INTENT", "语音请求类型");

    private String type;
    /**
     * 提示，仅作阅读代码的提示。
     */
    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    RokidActionTypeEnums(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static RokidActionTypeEnums convert(String type) {
        for (RokidActionTypeEnums v : RokidActionTypeEnums.values()) {
            if (StringUtils.equals(v.type, type)) {
                return v;
            }
        }
        return null;
    }

}
