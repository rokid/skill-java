package com.rokid.skill.kit.core;

import com.rokid.skill.protocol.request.ReqRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Skill请求切片.
 *
 * @author wuyukai on 2018/7/6.
 */
@Component
public class SkillInterceptorChain {

  private final List<SkillInterceptor> interceptors = new ArrayList<>();

  @Autowired
  public SkillInterceptorChain(
      ObjectProvider<List<SkillInterceptor>> listObjectProvider) {
    this.interceptors.add(new PreInterceptor());
    listObjectProvider.ifAvailable(this.interceptors::addAll);
  }

  void before(ReqRequest reqRequest) {

    interceptors.forEach(skillInterceptor -> {
      if (skillInterceptor.support(reqRequest)) {
        skillInterceptor.before(reqRequest);
      }
    });
  }

  void after(ReqRequest reqRequest) {
    for (int i = interceptors.size() - 1; i >= 0; i--) {
      interceptors.get(i).after(reqRequest);
    }
  }
}
