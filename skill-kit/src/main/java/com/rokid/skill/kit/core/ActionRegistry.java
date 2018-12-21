package com.rokid.skill.kit.core;

import com.rokid.skill.kit.annotation.Action;
import com.rokid.skill.kit.annotation.Processor;
import com.rokid.skill.kit.exception.SkillKitCode;
import com.rokid.skill.kit.exception.SkillKitException;
import com.rokid.skill.protocol.request.ReqRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.assertj.core.util.Arrays;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContextException;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * Action注解注入服务.
 *
 * @author wuyukai on 2018/5/5.
 */
@Component
@Order
@Slf4j
public class ActionRegistry implements BeanPostProcessor {

  private final Map<String, Handler> router = new HashMap<>();

  @Override
  public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
    return o;
  }

  @Override
  public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
    Class beanType = o.getClass();
    if (beanType != null && isHandler(beanType)) {
      Method[] methods = ReflectionUtils.getAllDeclaredMethods(beanType);
      for (Method m : methods) {
        Action annotation = m.getAnnotation(Action.class);
        if (annotation != null) {
          int paramCount = m.getParameterCount();
          if (paramCount != 1) {
            log.error(
                "method parameter count is not correct when @Action, method description is : {}",
                m.getName());
            throw new SkillKitException(SkillKitCode.KIT_ERROR,
                "method parameter count is not correct when @Action, method description is " + m
                    .getName());
          }
          Type[] types = m.getGenericParameterTypes();
          if (types[0] != ReqRequest.class) {
            log.error("method parameter type is not ReqRequest, method description is : {}",
                m.getName());
            throw new SkillKitException(SkillKitCode.KIT_ERROR,
                "method parameter type is not ReqRequest, method description is " + m.getName());
          }

          Handler handler = new Handler(m, o);
          String[] events = annotation.event();
          String[] intents = annotation.intent();
          boolean isEvent = !Arrays.isNullOrEmpty(events);
          boolean isIntent = !Arrays.isNullOrEmpty(intents);

          // actionType必为intent、event二者中其一，两种类型注解有且仅有一个满足时为正确逻辑，当不存在注解或两个注解都存在时抛出异常。
          if (isEvent == isIntent) {
            throw new ApplicationContextException(
                "expect single annotation field but has 0 or 2, "
                    + "check whether it is correct when you use @Action annotation");
          }

          String[] actions = ArrayUtils.addAll(intents, events);
          // 去重校验
          for (String action : actions) {
            if (router.get(action) != null) {
              log.error("action : {} is duplication", action);
              throw new ApplicationContextException(
                  "action: " + action + " can't have the same value when use the @Action");
            }
            router.put(action, handler);
          }
        }
      }
    }
    return o;
  }

  Map<String, Handler> getRouter() {
    return router;
  }

  private boolean isHandler(Class<?> beanType) {
    return AnnotatedElementUtils.hasAnnotation(beanType, Processor.class);
  }
}
