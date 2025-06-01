package com.example.demo.domain.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @GetMapping("/list")
    public String index() {
        return "employeeManagement/EmployeeInfoView";
    }

    @GetMapping("/add")
    public String add() {
        return "employeeManagement/EmployeeRegisterView";
    }
}
