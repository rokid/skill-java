package com.rokid.skill.kit.core;

import com.rokid.skill.kit.bean.ReqBasicInfo;
import com.rokid.skill.kit.constants.InternalIntent;
import com.rokid.skill.kit.util.ProtocolUtil;
import com.rokid.skill.kit.util.RequestUtil;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.response.ResResponse;
import java.lang.reflect.Method;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Skill服务请求分发服务.
 *
 * @author wuyukai on 2018/5/5.
 */
@Slf4j
@Component("speechletDispatcher")
public class SkillDispatcher implements SpeechletDispatcher {

  private final ActionRegistry actionRegistry;

  private final SkillInterceptorChain skillInterceptorChain;

  @Autowired
  public SkillDispatcher(ActionRegistry actionRegistry,
      SkillInterceptorChain skillInterceptorChain) {
    this.actionRegistry = actionRegistry;
    this.skillInterceptorChain = skillInterceptorChain;
  }

  @Override
  public ResResponse handle(ReqRequest reqRequest) throws Exception {
    skillInterceptorChain.before(reqRequest);
    ReqBasicInfo basicInfo = RequestUtil.getBasicInfo(reqRequest);
    Map<String, Handler> router = actionRegistry.getRouter();
    String actionName = basicInfo.getActionName();
    Handler handler = router.get(actionName);
    if (handler == null) {
      log.warn(
          "can not find a handler to deal this request, the actionName is : {},"
              + " maybe the intent or event does not register",
          actionName);
      if (StringUtils.equals(actionName, InternalIntent.WELCOME_INTENT)) {
        return ProtocolUtil.playTts("欢迎进入若琪技能");
      }
      return ProtocolUtil.ignore();
    }
    Method method = handler.getMethod();
    Object bean = handler.getBean();
    ResResponse resResponse = (ResResponse) method.invoke(bean, reqRequest);
    skillInterceptorChain.after(reqRequest);
    return resResponse;
  }

}
