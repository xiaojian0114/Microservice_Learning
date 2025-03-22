package org.example.gatewayservice.config;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class SentinelRuleConfig {

    @PostConstruct
    public void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        // 这里 resourceName 建议和网关路由 id 或者你的具体资源标识保持一致
        rule.setResource("mqtt_route");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(1);  // 限制 QPS 为 5
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
