package com.rokid.skill.kit4j.core;

import com.rokid.skill.protocol.request.ReqRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wuyukai on 2018/7/6.
 */
@Component
public class SkillInterventionChain {

  private final List<SkillIntervention> interventions;

  @Autowired
  public SkillInterventionChain(Optional<List<SkillIntervention>> optionalSkillInterventions) {
    this.interventions = optionalSkillInterventions.orElseGet(ArrayList::new);
  }

  void before(ReqRequest reqRequest) {

    for (SkillIntervention intervention : interventions) {
      if (intervention.support(reqRequest)) {
        intervention.before(reqRequest);
      }
    }
  }

  void after(ReqRequest reqRequest) {
    for (int i = interventions.size() - 1; i >= 0; i--) {
      interventions.get(i).after(reqRequest);
    }
  }
}
