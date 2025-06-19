package com.example.demo.domain.departmentwork.dto;

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
    private String managerName;   // workManager 문자열
    private Boolean isPublic;     // workAgree → Boolean으로 처리
    private String category;      // workCategory
    private Boolean alarmSetting;
    private String linkedUnitWork;
    private String attachmentPath;
    private Long departmentId;
}
