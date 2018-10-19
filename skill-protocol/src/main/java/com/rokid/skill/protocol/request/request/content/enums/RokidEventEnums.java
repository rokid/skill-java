package com.rokid.skill.protocol.request.request.content.enums;


import org.apache.commons.lang3.StringUtils;

/**
 * @author wuyukai
 */

public enum RokidEventEnums {

    /**
     * 当MediaPlayer开始播放时发生。
     */
    MEDIA_STARTED_EVENT("Media.STARTED", "EventRequest,event=Media.STARTED"),

    // 当MediaPlayer播放失败时发生。
    MEDIA_FAILED_EVENT("Media.FAILED", "EventRequest,event=Media.FAILED"),

    /**
     * 当MediaPlayer中途停止时发生。
     */
    MEDIA_PAUSED_EVENT("Media.PAUSED", "EventRequest,event=Media.PAUSED"),

    /**
     * 当播放内容结束时发生。
     */
    MEDIA_FINISHED_EVENT("Media.FINISHED", "EventRequest,event=Media.FINISHED"),

    /**
     * 当Voice开始播放时发生。
     */
    VOICE_STARTED_EVENT("Voice.STARTED", "EventRequest,event=Voice.STARTED"),


    /**
     * 当Voice播放失败时发生。
     */
    VOICE_FAILED_EVENT("Voice.FAILED", "EventRequest,event=Voice.FAILED"),

    /**
     * 当Voice播放内容结束时发生。
     */
    VOICE_FINISHED_EVENT("Voice.FINISHED", "EventRequest,event=Voice.FINISHED"),

    /**
     * 当应用退出的时候
     */
    SKILL_EXIT_EVENT("Skill.EXIT", "EventRequest,event=Skill.EXIT"),

    /**
     * 当Domain被切换到的时候的事件，可以用于关闭资源
     */
    SESSION_ENDED_EVENT("Session.ENDED", "EventRequest,event=Session.ENDED");

    /**
     * 事件名称
     */
    private String event;

    /**
     * 提示，仅作阅读代码的提示。
     */
    private String desc;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    RokidEventEnums(String event, String desc) {
        this.event = event;
        this.desc = desc;
    }

    public static RokidEventEnums convert(String event) {
        for (RokidEventEnums v : RokidEventEnums.values()) {
            if (StringUtils.equals(v.event, event)) {
                return v;
            }
        }
        return null;
    }
}
