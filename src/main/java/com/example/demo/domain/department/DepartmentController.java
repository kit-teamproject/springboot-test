package com.example.demo.domain.department;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @GetMapping("/list")
    public String index() {
        return "departmentWork/workSearch";
    }

    @GetMapping("/add")
    public String add() {
        return "departmentWork/workRegister";
    }
}
