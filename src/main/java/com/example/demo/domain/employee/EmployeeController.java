package com.example.demo.domain.employee;

import com.example.demo.domain.employee.dto.ReqEmployeeForm;
import com.example.demo.domain.employee.dto.ResEmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/list")
    public String index(@RequestParam(value = "userid", required = false) String userid, Model model) {
        List<ResEmployeeDto> list = (userid != null && !userid.isBlank())
                ? employeeService.findByUserid(userid).map(List::of).orElse(List.of())
                : employeeService.getAll();

        model.addAttribute("employees", list);
        model.addAttribute("keyword", userid);
        return "employeeManagement/EmployeeInfoView";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("ReqEmployeeForm", new ReqEmployeeForm());
        return "employeeManagement/EmployeeRegisterView";
    }

    @PostMapping("/add")
    public String addSubmit(@ModelAttribute ReqEmployeeForm form) {
        employeeService.register(form);
        return "redirect:/employee/list";
    }
}
