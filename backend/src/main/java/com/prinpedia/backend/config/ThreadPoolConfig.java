package com.prinpedia.backend.config;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolTaskExecutor threadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数目
        executor.setCorePoolSize(50);
        //指定最大线程数
        executor.setMaxPoolSize(300);
        //队列中最大的数目
        executor.setQueueCapacity(80);
        //线程名称前缀
        executor.setThreadNamePrefix("threadPool");
        //rejection-policy：当pool已经达到max size的时候，如何处理新任务
        //CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        //对拒绝task的处理策略
        executor.setRejectedExecutionHandler(
                new ThreadPoolExecutor.CallerRunsPolicy());
        //线程空闲后的最大存活时间
        executor.setKeepAliveSeconds(60);
        //加载
        executor.initialize();
        return executor;
    }
}
