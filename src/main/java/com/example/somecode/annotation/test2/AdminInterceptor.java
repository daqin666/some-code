package com.example.somecode.annotation.test2;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod method=(HandlerMethod) handler;
        //判断是否有adminIntercept注解
        AdminIntercept adminIntercept = method.getMethodAnnotation(AdminIntercept.class);
        if (adminIntercept==null||!adminIntercept.required()){
            //没有注解或注解的required为false，直接放行
            return true;
        }
        //获取会话中的用户
        User user=(User)request.getSession().getAttribute("user");
        //判断用户权限
        if (user==null){
            System.out.println("用户未登录");
            return false;
        }
        if(user.getRole()==null||!"admin".equals(user.getRole())){
            System.out.println("用户没有admin权限");
            return false;
        }
        return true;
    }
}
