package com.ariche.konamiapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Properties class getting values from the application.properties file. Feeds into the
 * AsyncConfig class.
 */
@Configuration
@ConfigurationProperties(prefix = "async")
@Data
public class AsyncConfigProperties {
    private int corePoolSize;
    private int maxPoolSize;
    private int queueCapacity;
    private String threadNamePrefix;
}
