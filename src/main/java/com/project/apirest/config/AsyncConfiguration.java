/**
 * @since 1.0
 * @author Gonzalo Orellano
 * @version 1.0
 */
package com.project.apirest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**Async Configuration.**/
@Configuration
@EnableAsync
public class AsyncConfiguration {
    /**CORE_POOL_SIZE.*/
    @Value("${executor.corePoolSize: 100}")
    private Integer corePoolSize;
    /**MAX_POOL_SIZE.**/
    @Value("${executor.maxPoolSize: 100}")
    private static Integer maxPoolSize;
    /**QUEUE_CAPACITY.**/
    @Value("${executor.queueCapacity: 100}")
    private static Integer queueCapacity;

    /** Thread Pool Task Executor.
     * @return Async Executor.
     */
    @Bean("threadPoolTaskExecutor")
    public final Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.initialize();

        return executor;
    }
}
