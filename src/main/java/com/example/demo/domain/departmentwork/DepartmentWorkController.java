package com.example.demo.domain.departmentwork;

import com.example.demo.domain.departmentwork.dto.ReqDepartmentWorkForm;
import com.example.demo.domain.departmentwork.dto.ResDepartmentWorkDto;
import com.example.demo.domain.department.DepartmentRepository;
import com.example.demo.domain.employee.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
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
                                 @RequestParam(required = false) String workManager,
                                 @RequestParam(required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                 @RequestParam(required = false)
                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<ResDepartmentWorkDto> result = workService.searchWork(workName, workManager, startDate, endDate);
        model.addAttribute("workList", result);
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());

        return "departmentWork/workSearch";
    }

    // 2. 등록 페이지 이동
    @GetMapping("/add")
    public String showRegisterPage(@ModelAttribute("form") ReqDepartmentWorkForm form, Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("departments", departmentRepository.findAll());
        return "departmentWork/workRegister";
    }


    // 3. 등록 처리
    @PostMapping("/add")
    public String register(@Valid @ModelAttribute("form") ReqDepartmentWorkForm form,
                           BindingResult bindingResult,
                           Model model) throws IOException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("employees", employeeRepository.findAll());
            model.addAttribute("departments", departmentRepository.findAll());
            return "departmentWork/workRegister";
        }

        workService.registerWork(form);
        return "redirect:/departmentWork/list";
    }
}