package com.example.demo.domain.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String userid, Model model) {
        List<EmployeeEntity> employees;

        if (userid != null && !userid.isBlank()) {
            Optional<EmployeeEntity> result = employeeService.findByUserid(userid);
            employees = result.map(List::of).orElse(List.of());
        } else {
            employees = employeeService.getAllEmployees();
        }

        model.addAttribute("employees", employees);
        return "employeeManagement/EmployeeInfoView";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("employee", new EmployeeEntity());
        return "employeeManagement/EmployeeRegisterView";
    }

    @PostMapping("/add")
    public String addSubmit(@ModelAttribute("employee") EmployeeEntity employee) {
        employeeService.save(employee);
        return "redirect:/employee/list";
    }
}
