package com.example.demo.domain.learningclub.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class ReqLearningClubForm {
    private String name;
    private String description;
    private MultipartFile image;
}
