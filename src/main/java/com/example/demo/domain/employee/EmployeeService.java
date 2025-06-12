package com.example.demo.domain.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public void save(EmployeeEntity employee) {
        employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<EmployeeEntity> findByUserid(String userid) {
        return employeeRepository.findByUserid(userid);
    }
}
