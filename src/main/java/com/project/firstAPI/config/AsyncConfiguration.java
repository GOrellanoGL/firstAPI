package com.project.firstAPI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfiguration {
    @Value("${executor.corePoolSize: 100}")
    private Integer CORE_POOL_SIZE;

    @Value("${executor.maxPoolSize: 100}")
    private Integer MAX_POOL_SIZE;

    @Value("${executor.queueCapacity: 100}")
    private Integer QUEUE_CAPACITY;

    @Bean("threadPoolTaskExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.initialize();

        return executor;
    }
}
