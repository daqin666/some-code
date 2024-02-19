package com.example.somecode.threadpool.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncSendEmailEventHandler implements ApplicationListener<AsyncSendEmailEvent> {


    @Async("syncExecutor")
    @Override
    public void onApplicationEvent(AsyncSendEmailEvent event) {
        if (event == null) {
            return;
        }

        String email = event.getEmail();
        String subject = event.getSubject();
        String content = event.getContent();
        String targetUserId = event.getTargetUserId();
    }
}
