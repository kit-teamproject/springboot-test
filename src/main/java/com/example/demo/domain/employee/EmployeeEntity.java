package com.example.demo.domain.employee;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employee_id;
    private String userid;
    private String password;
    private String phone;
    private String name;
    private String address;
    private String role;
    private String department;
    private Boolean sms_receive;
}
