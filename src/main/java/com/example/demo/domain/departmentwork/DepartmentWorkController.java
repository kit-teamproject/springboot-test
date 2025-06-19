package com.example.demo.domain.departmentwork;

import com.example.demo.domain.departmentwork.dto.ReqDepartmentWorkForm;
import com.example.demo.domain.departmentwork.dto.ResDepartmentWorkDto;
import com.example.demo.domain.department.DepartmentRepository;
import com.example.demo.domain.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/departmentWork")
public class DepartmentWorkController {

    private final DepartmentWorkService workService;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    // 1. 조회 페이지 이동
    @GetMapping("/list")
    public String showSearchPage(Model model,
                                 @RequestParam(required = false) String workName,
                                 @RequestParam(required = false) String workManager) {
        List<ResDepartmentWorkDto> result = workService.searchWork(workName, workManager);
        model.addAttribute("workList", result);
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());

        return "departmentWork/workSearch";
    }

    // 2. 등록 페이지 이동
    @GetMapping("/add")
    public String showRegisterPage(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll()); // 여기에 추가
        return "departmentWork/workRegister";
    }

    // 3. 등록 처리
    @PostMapping(value = "/add")
    public String register(@ModelAttribute ReqDepartmentWorkForm form) throws IOException {
        workService.registerWork(form);
        return "redirect:/departmentWork/list";
    }
}