package com.example.demo.domain.schedule.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ReqScheduleForm {

    private String scheduleName;

    private String startDate; // yyyy-MM-dd 형식

    private String endDate;

    private String shareYn; // "true"/"false" 문자열로 넘어올 가능성 → Boolean 변환 가능

    private String viewableEmployeeIds; // 콤마 구분 문자열

    private Integer importance; // 중요도 (1~5)

    private String sendSmsYn; // "true"/"false" 문자열로 넘어올 가능성 → Boolean 변환 가능


    //사용자 데이터 받기
    private Long emp_id;
    private String emp_userid;
}