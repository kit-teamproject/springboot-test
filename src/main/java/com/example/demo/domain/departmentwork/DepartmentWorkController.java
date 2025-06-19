package com.example.demo.domain.departmentwork;

import com.example.demo.domain.department.dto.ReqDepartmentWorkForm;
import com.example.demo.domain.department.dto.ResDepartmentWorkDto;
import com.example.demo.domain.employee.EmployeeRepository;
import com.example.demo.domain.departmentwork.DepartmentWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructo_r
@RequestMapping("/departmentwork")
public class DepartmentWorkController {

    private final WorkService workService;
    private final EmployeeRepository employeeRepository;
    // 1. 조회 페이지 이동
    @GetMapping("/list")
    public String showSearchPage(Model model,
                                 @RequestParam(required = false) String workName,
                                 @RequestParam(required = false) Long workManager) {
        List<ResWorkDto> result = workService.searchWork(workName, workManager);
        model.addAttribute("workList", result);
        model.addAttribute("employees", employeeRepository.findAll());
        return "departmentWork/workSearch";
    }

    // 2. 등록 페이지 이동
    @GetMapping("/add")
    public String showRegisterPage(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "departmentWork/workRegister";
    }

    // 3. 등록 처리
    @PostMapping("/add")
    public String register(@ModelAttribute ReqWorkForm form) throws IOException {
        workService.registerWork(form);
        return "redirect:/departmentWork/list";
    }
}
