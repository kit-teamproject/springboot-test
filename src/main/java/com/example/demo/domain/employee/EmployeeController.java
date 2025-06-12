package com.example.demo.domain.employee;

import com.example.demo.domain.employee.dto.ReqEmployeeForm;
import com.example.demo.domain.employee.dto.ResEmployeeDto;
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
        List<ResEmployeeDto> employees;

        if (userid != null && !userid.isBlank()) {
            ResEmployeeDto result = employeeService.findByUserid(userid);
            employees = result != null ? List.of(result) : List.of();
        } else {
            employees = employeeService.getAllEmployees();
        }

        model.addAttribute("employees", employees);
        return "employeeManagement/EmployeeInfoView";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("employee", new ReqEmployeeForm());
        return "employeeManagement/EmployeeRegisterView";
    }

    @PostMapping("/add")
    public String addSubmit(@ModelAttribute("employee") ReqEmployeeForm reqEmployeeForm) {
        employeeService.save(reqEmployeeForm);
        return "redirect:/employee/list";
    }
}
