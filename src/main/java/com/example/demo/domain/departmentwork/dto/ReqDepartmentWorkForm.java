package com.example.demo.domain.departmentwork.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
public class ReqDepartmentWorkForm {

    @NotBlank(message = "업무명을 입력하세요.")
    private String workName;

    @NotNull(message = "시작일을 선택하세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "종료일을 선택하세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull(message = "공개 여부를 선택하세요.")
    private Boolean isPublic;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate openStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate openEndDate;

    @NotBlank(message = "담당자를 선택하세요.")
    private String workManager;

    private String linkedUnitWork;

    private Boolean alarmSetting;

    private MultipartFile attachment;

    @NotBlank(message = "업무 범주를 입력하세요.")
    private String category;

    @NotNull(message = "부서를 선택하세요.")
    private Long departmentId;
}