package com.example.demo.domain.departmentwork.dto;

import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
public class ReqDepartmentWorkForm {

    private String workName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private Boolean isPublic; // workAgree

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate openStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate openEndDate;

    private String workManager; // DB에서 문자열(이름/ID)로 저장됨

    private String linkedUnitWork;

    private Boolean alarmSetting; // workAlarmSetting

    private MultipartFile attachment; 

    private String category;

    private Long departmentId;
}
