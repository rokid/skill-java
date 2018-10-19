package com.rokid.skill.protocol.response.response.action.directive.voice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rokid.skill.protocol.response.response.action.directive.ResDirective;
import com.rokid.skill.protocol.response.response.action.directive.voice.item.ResVoiceItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Bassam
 * @date 25/03/2017
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResVoice extends ResDirective {

    public ResVoice() {
        setType("voice");
    }

    /**
     * 从头开始播放操作，最好带上VoiceItem，否则一旦设备那边没有Item，则会出错
     */
    public static final String VOICE_ACTION_PLAY = "PLAY";
    /**
     * 暂停播放操作，暂停当前的Voice播放
     */
    public static final String VOICE_ACTION_PAUSE = "PAUSE";
    /**
     * 继续播放操作，继续当前的播放，需要注意的是客户端那边必须要有VoiceItem，否则会出错（可以校验一下当前的Voice状态是否是PAUSE的状态）
     */
    public static final String VOICE_ACTION_RESUME = "RESUME";
    /**
     * 停止播放操作，停止当前的VOICE播放
     */
    public static final String VOICE_ACTION_STOP = "STOP";

    private boolean disableEvent;
    /**
     * 播放控制操作 "PLAY/PAUSE/RESUME/STOP"
     */
    private String action;
    /**
     * tts 内容
     */
    private ResVoiceItem item;
}
