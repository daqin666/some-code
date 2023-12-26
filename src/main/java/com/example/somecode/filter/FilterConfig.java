package com.example.somecode.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    @Autowired
    private WeFilter weFilter1;

    @Autowired
    private WeFilter weFilter2;

    @Bean
    public FilterRegistrationBean injectFilterOne() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(weFilter1);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("filter_one");
        // 优先级
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean injectFilterTwo() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(weFilter2);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("filter_one");
        // 优先级
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
