package com.example.demo.domain.home;

import com.example.demo.domain.employee.EmployeeRepository;
import com.example.demo.domain.employee.dto.ResEmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final EmployeeRepository employeeRepository;

    //    메인페이지
    @GetMapping("/")
    public String home(Model model) {
        List<ResEmployeeDto> employees = employeeRepository.findAll()
                .stream()
                .map(ResEmployeeDto::employeeEntityToDto)
                .toList();

        model.addAttribute("employees", employees);

        return "home";
    }
}
