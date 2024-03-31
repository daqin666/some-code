package com.example.somecode.cache.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineConfig {
    @Bean
    public Cache caffeineCache(){
        return Caffeine.newBuilder()
                //设置10秒后过期,方便后续观察现象
                .expireAfterWrite(10, TimeUnit.SECONDS)
                //初始容量为100
                .initialCapacity(100)
                //最大容量为200
                .maximumSize(200)
                .build();
    }

    //定义manualCaffeineCache，用来演示手动加载
    @Bean
    @Qualifier(value = "manualCaffeineCache")
    public Cache manualCaffeineCache(){
        return Caffeine.newBuilder()
                .expireAfterWrite(10,TimeUnit.SECONDS)
                .initialCapacity(50)
                .maximumSize(100)
                .build();
    }

//    @Bean
//    @Qualifier(value = "listenerCaffeineCache")
//    public Cache listenerCaffeineCache(){
//        return Caffeine.newBuilder()
//                .expireAfterWrite(10,TimeUnit.SECONDS)
//                .initialCapacity(100)
//                .maximumSize(200)
//                .evictionListener(new RemovalListener<String, Object>() {
//                    @Override
//                    public void onRemoval(@Nullable String key, @Nullable Object value, @NonNull RemovalCause removalCause) {
//                        System.out.println("evictionListener:key="+key+",value="+value+",removalCause="+removalCause);
//                    }
//                }).removalListener((key,value,cause)->{
//                    System.out.println("removalListener:key="+key+",value="+value+",cause="+cause);
//                })
//                .build();
//    }
}
