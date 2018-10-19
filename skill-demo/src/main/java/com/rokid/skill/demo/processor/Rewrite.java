package com.rokid.skill.demo.processor;

import com.rokid.skill.kit4j.core.AbstractRewrite;
import com.rokid.skill.protocol.request.ReqRequest;
import com.rokid.skill.protocol.request.request.content.slot.Slot;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * intent 及 slot 改写功能，高级特性，不使用可忽略
 *
 * @author wuyukai on 2018/7/8.
 */
@Component
public class Rewrite extends AbstractRewrite {

    @Override
    public String rewriteAction(String originalActionName) {
        return originalActionName;
    }

    @Override
    public Map<String, Slot> rewriteSlots(Map<String, Slot> originalSlots) {
        return originalSlots;
    }

    @Override
    public boolean support(ReqRequest reqRequest) {
        return false;
    }
}
