package com.example.somecode.annotation.test1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

@RestController
public class DemoController {
    @GetMapping("/testMethod1")
    public String testMethod1()throws Exception{
        Person person=new Person("cxy","134****8118",22,"男");
        Method[] methods = Person.class.getMethods();
        String res="";
        for (Method m : methods) {
            String methodName=m.getName();
            if (!methodName.contains("get")||methodName.equals("getClass")){
                continue;
            }
            ArgIntercept declaredAnnotation = m.getDeclaredAnnotation(ArgIntercept.class);
            //当ArgIntercept注解值为true时，跳过
            if (declaredAnnotation!=null&&declaredAnnotation.required()){
                continue;
            }
            //只有没有ArgIntercept或者ArgIntercept的required为false时，才拼接字符串
            String temp=String.valueOf(m.invoke(person))+" ";
            res=res+temp;
        }
        return res;
    }
}
