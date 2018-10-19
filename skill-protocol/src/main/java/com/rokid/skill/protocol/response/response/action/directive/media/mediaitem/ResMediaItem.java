package com.rokid.skill.protocol.response.response.action.directive.media.mediaitem;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

/**
 * @author Bassam
 * @date 25/03/2017
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResMediaItem {

    public static final String MEDIA_TYPE_AUDIO = "AUDIO";
    public static final String MEDIA_TYPE_VIDEO = "VIDEO";
    private String itemId;
    private String token;
    private String type;
    private String url;
    private long offsetInMilliseconds;

}
