package com.example.somecode.annotation.test4;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test4Controller {

    @MyLog
    @RequestMapping("/add")
    public String add(){
        return "yes";
    }

}
