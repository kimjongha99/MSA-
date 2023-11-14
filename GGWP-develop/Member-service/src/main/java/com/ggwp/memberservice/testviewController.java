package com.ggwp.memberservice;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testviewController {
    @GetMapping("/member/signupform")
    public String signupForm(){
        return "/signup";
    }
}
