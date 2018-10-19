package com.rokid.skill.protocol.request.request.content.extra.media;

import lombok.Data;

/**
 * media 事件透传数据
 *
 * @author wuyukai
 * @date 22/07/2018
 */
@Data
public class ReqExtraMedia {

    /**
     * 播放项 id
     */
    private String itemId;

    /**
     * token，用于校验，可为空
     */
    private String token;

    /**
     * 进度条，单位毫秒
     */
    private String progress;

    /**
     * media 总时长，单位毫秒
     */
    private String duration;

    private String state;
}
