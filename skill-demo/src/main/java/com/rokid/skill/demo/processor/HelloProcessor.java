package com.rokid.skill.demo.processor;

import com.rokid.skill.demo.common.ProtocolUtils;
import com.rokid.skill.kit4j.aop.Action;
import com.rokid.skill.kit4j.aop.Processor;
import com.rokid.skill.kit4j.constants.SystemIntent;
import com.rokid.skill.kit4j.log.LogStatistics;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.request.request.content.slot.Slot;
import com.rokid.skill.protocol.response.ResResponse;
import com.rokid.skill.protocol.utils.RequestUtils;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * 外卖技能 demo，confirm 和 pickUp 用法 demo
 *
 * @author wuyukai on 2018/5/6.
 */
@Slf4j
@Processor
public class HelloProcessor {

    /**
     * 技能打开意图
     *
     * @return 进入直接confirm
     */
    @Action(intent = SystemIntent.WELECOME_INTENT)
    public ResResponse welcome(ReqRequest reqRequest) {
        //日志打印测试用例
        log.info("enter pick up ...");
        //日志扩展字段测试用例
        LogStatistics.addExtra("voiceId", "1234567890");
        return ProtocolUtils.pickUp("欢迎进入外卖，请问你想吃什么");
    }

    /**
     * eat 意图
     *
     * @return 响应
     */
    @Action(intent = Intent.EAT)
    public ResResponse eat(ReqRequest reqRequest) {
        //confirm 测试用例
        return ProtocolUtils.confirm("你想吃什么", "food", "food", null);
    }

    /**
     * confirm 意图
     *
     * @return 响应
     */
    @Action(intent = Intent.FOOD)
    public ResResponse food(ReqRequest reqRequest) {
        log.info("food");
        Map<String, Slot> slotMap = RequestUtils.getSlots(reqRequest);
        Slot foodSlot = slotMap.get("food");
        if (foodSlot != null) {
            String food = foodSlot.getValue();
            return ProtocolUtils.playTts("好的，帮你预订了" + food);
        } else {
            return ProtocolUtils.playTts("未找到你需要的东西。");
        }
    }

}
