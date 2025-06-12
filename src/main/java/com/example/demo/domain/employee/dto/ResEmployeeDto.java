package com.example.demo.domain.employee.dto;

import com.example.demo.domain.employee.EmployeeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResEmployeeDto {
    private Long employee_id;
    private String userid;
    private String phone;
    private String name;
    private String address;
    private String role;
    private String department;
    private Boolean sms_receive;

    public static ResEmployeeDto employeeEntityToDto(EmployeeEntity e) {
        return ResEmployeeDto.builder()
                .employee_id(e.getEmployee_id())
                .userid(e.getUserid())
                .phone(e.getPhone())
                .name(e.getName())
                .address(e.getAddress())
                .role(e.getRole())
                .department(e.getDepartment())
                .sms_receive(e.getSms_receive())
                .build();
    }
}
