package com.example.demo.domain.v1.learningclub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/learning_club/")
public class LearningClubController {
    @GetMapping("/")
    public String index() {
        return "learning_club/index";
    }

    @GetMapping("/add")
    public String add() {
        return "learning_club/learning_club_add";
    }
}
