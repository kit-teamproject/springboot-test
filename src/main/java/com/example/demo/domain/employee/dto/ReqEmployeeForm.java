package com.example.demo.domain.employee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReqEmployeeForm {
    private String userid;
    private String password;
    private String phone;
    private String name;
    private String address;
    private String role;
    private String department;
    private Boolean sms_receive;
}
