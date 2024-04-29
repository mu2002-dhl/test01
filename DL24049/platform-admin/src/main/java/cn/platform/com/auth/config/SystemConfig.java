package cn.platform.com.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author lih
 * @Data 2024-02-18 16:32
 */
@ConfigurationProperties(prefix = "platform")
@Configuration
@Data
public class SystemConfig {
    private String name;

    private boolean loginEnable;
    private boolean loginRole;
}