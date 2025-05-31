package com.example.demo.domain.learningclub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/learning_club/")
public class LearningClubController {
    @GetMapping("/list")
    public String index() {
        return "learning_club/learning_club_list";
    }

    @GetMapping("/add")
    public String add() {
        return "learning_club/learning_club_add";
    }
}
