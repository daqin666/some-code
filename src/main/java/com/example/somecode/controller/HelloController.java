package com.example.somecode.controller;

import com.example.somecode.result.ResultBo;
import com.example.somecode.result.ResultUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class HelloController {

    @GetMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultBo hello(){
        return ResultUtil.success("hello world!");
    }
}
