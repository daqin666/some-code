package com.example.somecode.annotation.test2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    @AdminIntercept
    public User info(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        return user;
    }
}
