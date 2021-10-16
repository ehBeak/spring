package com.example.springreview.controller;

import com.example.springreview.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    private final MemberService memberService;

    @Autowired
    public HelloController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
