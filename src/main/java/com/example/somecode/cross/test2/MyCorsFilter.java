package com.example.somecode.cross.test2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Configuration
@WebFilter(filterName = "accessFilter", urlPatterns = "/*")
public class MyCorsFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {log.info("AccessFilter过滤器初始化！");}

    public void destroy() {}
}

//要想@WebFilter注解生效，需要在启动类上标注另外一个注解@ServletComponentScan用于扫描使其生效。
//@ServletComponentScan(value = {"com.example.demo.filter"})

