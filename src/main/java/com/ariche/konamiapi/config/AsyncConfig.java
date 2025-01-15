package com.ariche.konamiapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Configuration class for the Async functionality to handle multiple threads
 * This is configured from the properties class
 */
@Configuration
public class AsyncConfig {

    private final AsyncConfigProperties asyncConfigProperties;

    public AsyncConfig(AsyncConfigProperties asyncConfigProperties) {
        this.asyncConfigProperties = asyncConfigProperties;
    }

    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor(){
        return new ThreadPoolTaskExecutor(){{
            setCorePoolSize(asyncConfigProperties.getCorePoolSize());
            setMaxPoolSize(asyncConfigProperties.getMaxPoolSize());
            setQueueCapacity(asyncConfigProperties.getQueueCapacity());
            setThreadNamePrefix(asyncConfigProperties.getThreadNamePrefix());
            initialize();
        }};
    }
}
