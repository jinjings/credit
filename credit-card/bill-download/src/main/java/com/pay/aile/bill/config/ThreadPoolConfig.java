package com.pay.aile.bill.config;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/***
 * ThreadPoolConfig.java
 *
 * @author shinelon
 *
 * @date 2017年10月31日
 *
 */
@Configuration
public class ThreadPoolConfig {
    @Bean(name = "mailTaskExecutor")
    public ThreadPoolTaskExecutor cofigPool() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(64);
        pool.setMaxPoolSize(256);
        pool.setThreadNamePrefix("mail-download-task-");
        pool.setKeepAliveSeconds(30);
        pool.setQueueCapacity(2048);
        pool.setAllowCoreThreadTimeOut(true);
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }

    @Bean(name = "copyRelationTaskExecutor")
    public ThreadPoolTaskExecutor copyRelationTaskThreadPool() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(8);
        pool.setMaxPoolSize(32);
        pool.setThreadNamePrefix("copy-relation-task");
        pool.setKeepAliveSeconds(30);
        pool.setQueueCapacity(512);
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }
    @Bean(name = "nativeEmaileSearchExecutor")
    public ThreadPoolTaskExecutor nativeEmaileSearchExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(8);
        pool.setMaxPoolSize(32);
        pool.setThreadNamePrefix("native-emaile-search-task");
        pool.setKeepAliveSeconds(30);
        pool.setQueueCapacity(512);
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }
    @Bean(name = "nativeEmaileDownloadExecutor")
    public ThreadPoolTaskExecutor nativeEmaileDownloadExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(8);
        pool.setMaxPoolSize(32);
        pool.setThreadNamePrefix("native-emaile-task");
        pool.setKeepAliveSeconds(30);
        pool.setQueueCapacity(512);
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }
}
