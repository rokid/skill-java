package com.rokid.skill.protocol.request.context.device.location;


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
public class ReqLocation {

    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;

    /**
     * 国家
     */
    private String country;
    /**
     * 州/省份
     */
    private String state;
    /**
     * 城市
     */
    private String city;
    /**
     * 区县
     */
    private String area;
    /**
     * 地区，行政
     */
    private String district;
    /**
     * 街道
     */
    private String street;
    /**
     * 时区
     */
    private String timeZone;

}
