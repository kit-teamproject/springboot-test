package com.example.demo.domain.employee;

import com.example.demo.domain.employee.dto.ReqEmployeeForm;
import com.example.demo.domain.employee.dto.ResEmployeeDto;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public void save(ReqEmployeeForm reqEmployeeForm) {
        EmployeeEntity result = employeeRepository.findByUserid(reqEmployeeForm.getUserid())
                .orElse(null);
        if (result != null) {
            return;
        }
        //비밀번호 암호화
        String passwordEncoded = BCrypt.hashpw(reqEmployeeForm.getPassword(), BCrypt.gensalt());

        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .userid(reqEmployeeForm.getUserid())
                .password(passwordEncoded)
                .phone(reqEmployeeForm.getPhone())
                .name(reqEmployeeForm.getName())
                .address(reqEmployeeForm.getAddress())
                .role(reqEmployeeForm.getRole())
                .department(reqEmployeeForm.getDepartment())
                .sms_receive(reqEmployeeForm.getSms_receive())
                .build();
        employeeRepository.save(employeeEntity);
    }

    @Transactional(readOnly = true)
    public List<ResEmployeeDto> getAllEmployees() {
        List<ResEmployeeDto> result = employeeRepository.findAll()
                .stream()
                .map(ResEmployeeDto::employeeEntityToDto)
                .collect(Collectors.toList());

        return result;
    }

    @Transactional(readOnly = true)
    public ResEmployeeDto findByUserid(String userid) {
        ResEmployeeDto result = employeeRepository.findByUserid(userid)
                .map(ResEmployeeDto::employeeEntityToDto)
                .orElse(null);
        return result;
    }
}
