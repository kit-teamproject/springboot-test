package com.example.demo.domain.employee;

import com.example.demo.domain.employee.dto.ReqEmployeeForm;
import com.example.demo.domain.employee.dto.ResEmployeeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public void register(ReqEmployeeForm form) {
        EmployeeEntity e = new EmployeeEntity();
        e.setUserid(form.getUserid());
        e.setPassword(form.getPassword());
        e.setPhone(form.getPhone());
        e.setName(form.getName());
        e.setAddress(form.getAddress());
        e.setRole(form.getRole());
        e.setDepartment(form.getDepartment());
        e.setSms_receive(form.getSms_receive());

        employeeRepository.save(e);
    }

    @Transactional(readOnly = true)
    public List<ResEmployeeDto> getAll() {
        return employeeRepository.findAll().stream()
                .map(ResEmployeeDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ResEmployeeDto> findByUserid(String userid) {
        return employeeRepository.findByUserid(userid)
                .map(ResEmployeeDto::fromEntity);
    }
}
