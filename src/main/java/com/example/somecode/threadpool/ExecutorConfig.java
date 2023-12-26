package com.example.somecode.threadpool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync // 开启异步,可以放其他位置，比如启动类等
public class ExecutorConfig {

    @Bean("exportServiceExecutor")
    public ThreadPoolTaskExecutor exportServiceExecutor() {
        return getThreadPoolTaskExecutor("export-");
    }

    @Bean("export2ServiceExecutor")
    public ThreadPoolTaskExecutor export2ServiceExecutor() {
        return getThreadPoolTaskExecutor("export2-");
    }

    @Bean("syncExecutor")
    public ThreadPoolTaskExecutor syncExecutor() {
        return getThreadPoolTaskExecutor("sync-");
    }

    @Bean("haveReturnExecutor")
    public ThreadPoolTaskExecutor haveReturnExecutor() {
        return getThreadPoolTaskExecutor("haveReturn-");
    }

    private ThreadPoolTaskExecutor getThreadPoolTaskExecutor(String prefix) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // 核心线程数量：当前机器的核心数
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());

        // 最大线程数
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() * 2);

        // 队列大小
        executor.setQueueCapacity(200);

        // 空余线程存活时间
        executor.setKeepAliveSeconds(60);

        // 线程池中的线程名前缀
        executor.setThreadNamePrefix(prefix);

        // 拒绝策略：直接拒绝
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        // 执行初始化
        executor.initialize();

        return executor;
    }
}
