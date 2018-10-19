package com.rokid.skill.protocol.request.context.device.media;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Bassam
 * @date 25/03/2017
 */
@ToString
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqMedia {

    /**
     * 当前在播放状态
     */
    public static final String STATE_PLAYING = "PLAYING";
    /**
     * 当前播放器在暂停状态
     */
    public static final String STATE_PAUSED = "PAUSED";
    /**
     * 当前播放器状态在闲置状态
     */
    public static final String STATE_IDLE = "IDLE";
    /**
     * 未知状态
     */
    public static final String STATE_UNKNOW = "UNKNOW";
    /**
     * PLAYING PAUSED IDLE
     */
    @Getter
    @Setter
    private String state;

    public boolean isPlaying() {
        return STATE_PLAYING.equals(state);
    }

}
