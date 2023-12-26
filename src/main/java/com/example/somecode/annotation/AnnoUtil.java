package com.example.somecode.annotation;

import com.example.somecode.annotation.test1.ArgIntercept;
import com.example.somecode.annotation.test1.Person;
import com.example.somecode.annotation.test2.AdminIntercept;
import com.example.somecode.annotation.test3.MyAnnotation;
import com.example.somecode.annotation.test3.Test3Controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class AnnoUtil {

    /**
     * 获取类方法上的注解
     *
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public void getMethodAnno() throws InvocationTargetException, IllegalAccessException {
        Person person=new Person("cxy","134****8118",22,"男");
        Method[] methods = Person.class.getMethods();
        for (Method m : methods) {
            String methodName=m.getName();
            if (!methodName.contains("get")||methodName.equals("getClass")){
                continue;
            }
            ArgIntercept argIntercept = m.getDeclaredAnnotation(ArgIntercept.class);

            //当ArgIntercept注解值为true时，跳过
            if (argIntercept != null && argIntercept.required()) {
                continue;
            }
            //只有没有ArgIntercept或者ArgIntercept的required为false时，才拼接字符串
            String temp=String.valueOf(m.invoke(person));
            log.info(temp);
        }
    }

    /**
     * 通过反射获取类上的注解
     */
    public void getClassAnno() {
        // 反射
        for(Annotation a: Test3Controller.class.getAnnotations()){
            log.info("111111" + a);

            if(a instanceof MyAnnotation){
                MyAnnotation myAnnotation = (MyAnnotation) a;
                log.info("222222" + myAnnotation.messsge());
                log.info("222222" + myAnnotation.value());
            }
        }

        // 直接将MyAnnotation这注解取出
        MyAnnotation myAnnotation = Test3Controller.class.getAnnotation(MyAnnotation.class);
        if(myAnnotation !=null){
            log.info("333333" + myAnnotation.messsge());
        }
    }

    /**
     * 拦截器中获取类方法上的注解
     * @param request --
     * @param response --
     * @param handler --
     */
    public void preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HandlerMethod method=(HandlerMethod) handler;
        //判断是否有adminIntercept注解
        AdminIntercept adminIntercept = method.getMethodAnnotation(AdminIntercept.class);
        if (adminIntercept==null||!adminIntercept.required()){
            //没有注解或注解的required为false，直接放行
            return;
        }
        log.info("other...");
    }

    /**
     * 拦截器中获取类方法上的注解
     * @param request --
     * @param response --
     * @param handler --
     */
    public void preHandle2(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //必须强转为HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        //获取类上的注解
        AdminIntercept clazzAnnotation = handlerMethod.getBeanType().getAnnotation(AdminIntercept.class);

        //判断类上是否有打该注解
        boolean clazzAnnotationPresent = handlerMethod.getBeanType().isAnnotationPresent(AdminIntercept.class);

        //获取方法上的注解 方式1
        AdminIntercept methodAnnotation_1 = handlerMethod.getMethodAnnotation(AdminIntercept.class);

        //获取方法上的注解 方式2
        AdminIntercept methodAnnotation_2 = handlerMethod.getMethod().getAnnotation(AdminIntercept.class);

        //判断方法上是否有打该注解
        boolean methodsAnnotationPresent = handlerMethod.getMethod().isAnnotationPresent(AdminIntercept.class);

        log.info("other...");
    }



    /**
     * aop定义注解切点
     */
    public void getAnnoByAop() {
//        @Pointcut("@annotation(com.example.somecode.annotation.test4.MyLog)")
//        public  void logger(){}
    }
}
