package com.example.somecode.annotation.test3;

import java.lang.annotation.Annotation;

public class Test {
    public static void main(String[] args) {
        // 反射
        for(Annotation a: Test3Controller.class.getAnnotations()){
            System.out.println("111111" + a);

            if(a instanceof MyAnnotation){
                MyAnnotation myAnnotation = (MyAnnotation) a;
                System.out.println("222222" + myAnnotation.messsge());
                System.out.println("222222" + myAnnotation.value());
            }
        }

        // 直接将MyAnnotation这注解取出
        MyAnnotation myAnnotation = Test3Controller.class.getAnnotation(MyAnnotation.class);
        if(myAnnotation !=null){
            System.out.println("333333" + myAnnotation.messsge());
        }

    }
}
