package com.example.somecode.threadpool.async;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.TargetType;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class AsyncSendEmailEvent extends ApplicationEvent {
    /**
     * 邮箱
     **/
    private String email;
    /**
     * 主题
     **/
    private String subject;
    /**
     * 内容
     **/
    private String content;

    /**
     * 接收者
     **/
    private String targetUserId;

    public AsyncSendEmailEvent(Object source) {
        super(source);
    }
}
