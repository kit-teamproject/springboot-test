package com.example.demo.domain.schedule;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    @GetMapping("/list")
    public String index() {
        return "scheduleManagement/scheduleInquiry";
    }

    @GetMapping("/add")
    public String add() {
        return "scheduleManagement/addSchedule";
    }
}
