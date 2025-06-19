package com.example.demo.domain.department;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentRepository departmentRepository;

    @GetMapping("/department/add")
    public String showAddForm(Model model) {
        model.addAttribute("departments", departmentRepository.findAll());
        return "department/addForm"; // templates/department/addForm.html 로 연결됨
    }
}