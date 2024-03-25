package com.example.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Spring MVC
//브라우저와 통신하는 기술
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello"; //hello.html을 사용하도록 하겠다.
    }

}
