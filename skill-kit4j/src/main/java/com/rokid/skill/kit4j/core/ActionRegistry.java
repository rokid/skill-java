package com.rokid.skill.kit4j.core;

import com.rokid.skill.kit4j.aop.Processor;
import com.rokid.skill.kit4j.exception.KitException;
import com.rokid.skill.protocol.request.ReqRequest;
import java.lang.reflect.Type;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContextException;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import com.rokid.skill.kit4j.aop.Action;

/**
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
            log.error("method parameter count is not correct when @Action, method name is : {}", m.getName());
            throw new KitException(Code.KIT_ERROR, "method parameter count is not correct when @Action, method name is " + m.getName());
          }
          Type[] types = m.getGenericParameterTypes();
          if (types[0] != ReqRequest.class) {
            log.error("method parameter type is not ReqRequest, method name is : {}", m.getName());
            throw new KitException(Code.KIT_ERROR, "method parameter type is not ReqRequest, method name is " + m.getName());
          }

          Handler handler = new Handler(m, o);
          String event = annotation.event();
          String intent = annotation.intent();
          boolean isEvent = StringUtils.isNotEmpty(event);
          boolean isIntent = StringUtils.isNotEmpty(intent);

          // actionType必为intent、event二者中其一，两种类型注解有且仅有一个满足时为正确逻辑，当不存在注解或两个注解都存在时抛出异常。
          if (isEvent == isIntent) {
            throw new ApplicationContextException(
              "expect single annotation field but has 0 or 2, check whether it is correct when you use @Action annotation");
          }
          // 去重校验
          if (isIntent && router.get(intent) == null) {
            router.put(annotation.intent(), handler);
          } else if (isEvent && router.get(event) == null) {
            router.put(annotation.event(), handler);
          } else {
            throw new ApplicationContextException(
              "event or intent can't have the same value when use the @Action");
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
