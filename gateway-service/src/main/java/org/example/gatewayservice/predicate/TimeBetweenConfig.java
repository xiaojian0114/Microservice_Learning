package org.example.gatewayservice.predicate;



import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetTime;

@Configuration
@Data
@ConfigurationProperties(prefix = "gateway.time")
public class TimeBetweenConfig {

    @DateTimeFormat(pattern = "HH:mmXXX") // 使用ISO8601格式
    private OffsetTime start; // 类型改为OffsetTime

    @DateTimeFormat(pattern = "HH:mmXXX")
    private OffsetTime end;

    private String zone = "Asia/Shanghai"; // 保留时区配置
}