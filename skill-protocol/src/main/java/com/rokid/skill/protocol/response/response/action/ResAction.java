package com.rokid.skill.protocol.response.response.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rokid.skill.protocol.response.response.action.directive.ResDirective;
import java.util.List;
import lombok.Data;

/**
 * @author Bassam
 * @date 15/03/2017
 */
@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResAction {

    public static final String ACTION_TYPE_NORMAL = "NORMAL";
    public static final String ACTION_TYPE_EXIT = "EXIT";
    /**
     * 协议版本
     */
    public static final String ACTION_VERSION_V2 = "2.0.0";

    /**
     * "version": "2.0.0",
     */
    private String version;
    /**
     * 当前action的类型：NORMAL 或 EXIT。 当 type 是 NORMAL 时，voice 和media 会同时执行；当 type 是
     * EXIT时，action会立即退出，并且在这种情况下，voice 和 media 将会被会被忽略
     */
    private String type;
    /**
     * 当前action的展现形式：scene、cut、service。scene的action会在被打断后压栈，cut的action会在被打断后直接结束，service会在后台执行，但没有任何界面。该字段在技能创建时被确定，无法由cloudapp更改。
     */
    private String form;
    /**
     * 表明当此次返回的action执行完后 CloudAppClient是否要退出，同时，当 shouldEndSession 为 true时，CloudAppClient
     * 将会忽略EventRequests，即在action执行过程中不会产生EventRequest。
     */
    private boolean shouldEndSession;
    /**
     * 行为操作
     */
    private List<ResDirective> directives;


}
