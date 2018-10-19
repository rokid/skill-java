package com.rokid.skill.protocol.request.context.application.media;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * @author Bassam
 * @date 15/03/2017
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReqApplicationMedia {

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
     * "PLAYING / PAUSED / IDLE",
     */
    private String state;
    private String itemId;
    private String token;
    private String progress;
    private String duration;
}
