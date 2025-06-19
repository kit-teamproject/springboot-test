package com.example.demo.dmain.departmentwork.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
public class ReqDepartmentWorkForm {

    private String workName;

    private LocalDate startDate;
    private LocalDate endDate;

    private Boolean isPublic;

    private LocalDate openStartDate;
    private LocalDate openEndDate;

    private Long managerId; // EmployeeEntity와 연결될 ID

    private String linkedUnitWork;

    private Boolean alarmSetting;

    private MultipartFile attachment; // 파일 업로드용

    private String category;
}
