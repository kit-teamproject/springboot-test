package com.example.demo.dmain.departmentwork.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ResDepartmentWorkDto {

    private Long workId;
    private String workName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String managerName;
    private Boolean isPublic;
    private String category;
}
