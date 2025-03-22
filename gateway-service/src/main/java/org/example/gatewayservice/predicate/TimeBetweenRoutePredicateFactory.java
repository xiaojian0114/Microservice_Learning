package org.example.gatewayservice.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class TimeBetweenRoutePredicateFactory
        extends AbstractRoutePredicateFactory<TimeBetweenRoutePredicateFactory.Config> {

    // 配置类必须为静态内部类
    public static class Config {
        private String start;
        private String end;

        // Getter/Setter 必须存在
        public String getStart() { return start; }
        public void setStart(String start) { this.start = start; }
        public String getEnd() { return end; }
        public void setEnd(String end) { this.end = end; }
    }

    public TimeBetweenRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("start", "end"); // 定义参数顺序
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            try {
                final ZoneId zone = ZoneId.of("Asia/Shanghai");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mmXXX");

                // 换算栋八区
                OffsetTime startTime = OffsetTime.parse(config.getStart() + "+08:00", formatter);
                OffsetTime endTime = OffsetTime.parse(config.getEnd() + "+08:00", formatter);
                OffsetTime now = OffsetTime.now(zone);

                // 打印一下
                System.out.println("=== 时间检查 ===");
                System.out.println("配置开始时间: " + startTime);
                System.out.println("配置结束时间: " + endTime);
                System.out.println("当前东八区时间: " + now);

                boolean allowAccess = now.isAfter(startTime) && now.isBefore(endTime);
                System.out.println("是否允许访问: " + allowAccess);

                if (!allowAccess) {
                    exchange.getAttributes().put("TIME_BLOCKED", true);
                }
                return allowAccess;
            } catch (Exception e) {

                return false;
            }
        };
    }
}