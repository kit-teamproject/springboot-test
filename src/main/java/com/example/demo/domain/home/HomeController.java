package com.example.demo.domain.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //    메인페이지
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
