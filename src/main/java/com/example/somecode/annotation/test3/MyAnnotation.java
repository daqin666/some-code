package com.example.somecode.annotation.test3;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String value() default "";

    String messsge() default "aaaaaaaa";
}
