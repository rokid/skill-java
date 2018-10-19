package com.rokid.skill.protocol.request.request.content.enums;


import org.apache.commons.lang3.StringUtils;

/**
 * 系统级的Intent事件
 *
 * @author Bassam
 */
public enum RokidIntentEnums {

    /**
     * 通过激活词打开应用时会发送这个Intent
     */
    WELCOME_INTENT("ROKID.INTENT.WELCOME", "打开应用事件"),

    EXIT_INTENT("ROKID.INTENT.EXIT", "退出应用事件"),

    RESUME_INTENT("ROKID.INTENT.AUDIO_RESUME", "继续播放事件"),

    PAUSE_INTENT("ROKID.INTENT.AUDIO_PAUSE", "暂停播放事件"),

    STOP_INTENT("ROKID.INTENT.AUDIO_CANCEL", "取消播放事件"),

    LOOP_INTENT("ROKID.INTENT.AUDIO_LOOP_ON", "循环播放事件"),

    LOOP_OFF_INTENT("ROKID.INTENT.AUDIO_LOOP_OFF", "取消循环播放事件"),

    NEXT_INTENT("ROKID.INTENT.AUDIO_NEXT", "下一首播放事件"),

    PREVIOUS_INTENT("ROKID.INTENT.AUDIO_PREVIOUS", "上一首播放事件"),

    REPEAT_INTENT("ROKID.INTENT.AUDIO_REPEAT", "重复播放事件"),

    START_OVER_INTENT("ROKID.INTENT.AUDIO_START_OVER", "重新播放事件"),

    SHUFFLE_INTENT("ROKID.INTENT.AUDIO_SHUFFLE_ON", "随机播放事件"),

    SHUFFLE_OFF_INTENT("ROKID.INTENT.AUDIO_SHUFFLE_OFF", "关闭随机播放事件");

    /**
     * 预定义意图
     */
    private String intent;
    /**
     * 提示，仅作阅读代码的提示。
     */
    private String desc;

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    RokidIntentEnums(String intent, String desc) {
        this.intent = intent;
        this.desc = desc;
    }

    public static RokidIntentEnums convert(String intent) {
        for (RokidIntentEnums v : RokidIntentEnums.values()) {
            if (StringUtils.equals(v.intent, intent)) {
                return v;
            }
        }
        return null;
    }
}
