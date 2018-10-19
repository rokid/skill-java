package com.rokid.skill.kit4j.core;

import com.rokid.skill.kit4j.exception.KitException;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.response.ResResponse;
import com.rokid.skill.protocol.utils.ReqBasicInfo;
import com.rokid.skill.protocol.utils.RequestUtils;
import java.lang.reflect.Method;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wuyukai on 2018/5/5.
 */
@Slf4j
@Component("speechletDispatcher")
public class SkillDispatcher implements SpeechletDispatcher {

  private final ActionRegistry actionRegistry;

  private final SkillInterventionChain interventionChain;

  @Autowired
  public SkillDispatcher(ActionRegistry actionRegistry,
    SkillInterventionChain interventionChain) {
    this.actionRegistry = actionRegistry;
    this.interventionChain = interventionChain;
  }

  @Override
  public ResResponse handle(ReqRequest reqRequest) throws Exception {
    interventionChain.before(reqRequest);
    ReqBasicInfo basicInfo = RequestUtils.getBasicInfo(reqRequest);
    Map<String, Handler> router = actionRegistry.getRouter();
    String actionName = basicInfo.getActionName();
    Handler handler = router.get(actionName);
    if (handler == null) {
      throw new KitException(Code.KIT_ERROR,
        "can not find a handler to deal this request, maybe the intent or event does not register");
    }
    Method method = handler.getMethod();
    Object bean = handler.getBean();
    ResResponse responseResult = (ResResponse) method.invoke(bean, reqRequest);
    interventionChain.after(reqRequest);
    return responseResult;
  }

}
