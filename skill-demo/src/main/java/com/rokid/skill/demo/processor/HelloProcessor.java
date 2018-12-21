package com.rokid.skill.demo.processor;

import com.rokid.skill.demo.constant.Intent;
import com.rokid.skill.kit.annotation.Action;
import com.rokid.skill.kit.annotation.Processor;
import com.rokid.skill.kit.constants.InternalIntent;
import com.rokid.skill.kit.util.ProtocolUtil;
import com.rokid.skill.kit.util.RequestUtil;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.request.request.content.slot.Slot;
import com.rokid.skill.protocol.response.ResResponse;
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
  @Action(intent = InternalIntent.WELCOME_INTENT)
  public ResResponse welcome(ReqRequest reqRequest) {
    //日志打印测试用例
    log.info("enter pick up ...");
    return ProtocolUtil.pickUp("欢迎进入外卖，请问你想吃什么");
  }

  /**
   * eat 意图
   *
   * @return 响应
   */
  @Action(intent = Intent.EAT)
  public ResResponse eat(ReqRequest reqRequest) {
    //confirm 测试用例
    return ProtocolUtil.confirm("你想吃什么", "food", "food", null);
  }

  /**
   * confirm 意图
   *
   * @return 响应
   */
  @Action(intent = Intent.FOOD)
  public ResResponse food(ReqRequest reqRequest) {
    log.info("food");
    Map<String, Slot> slotMap = RequestUtil.getSlots(reqRequest);
    Slot foodSlot = slotMap.get("food");
    if (foodSlot != null) {
      String food = foodSlot.getValue();
      return ProtocolUtil.playTts("好的，帮你预订了" + food);
    } else {
      return ProtocolUtil.playTts("未找到你需要的东西。");
    }
  }

}
