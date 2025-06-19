package com.example.demo.domain.document.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ReqDocumentAddFormDto {
    private String title;
    private String description;
    private MultipartFile attachment;
    private List<String> categories;

    //사용자 데이터 받기
    private Long emp_id;
    private String emp_userid;
}
