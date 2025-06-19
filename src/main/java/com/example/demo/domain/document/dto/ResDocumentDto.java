package com.example.demo.domain.document.dto;

import com.example.demo.domain.document.DocumentEntity;
import com.example.demo.domain.employee.EmployeeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
public class ResDocumentDto {
    private Long id;
    private String title;
    private String contents;
    private String category;
    private String write_at;
    private String file_url;
    private String department_name;

    public static ResDocumentDto documentEntityToDto(DocumentEntity documentEntity) {
        String formatted = documentEntity.getWrite_at()
                .plusHours(9)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));


        return ResDocumentDto.builder()
                .id(documentEntity.getId())
                .title(documentEntity.getTitle())
                .contents(documentEntity.getContents())
                .category(documentEntity.getCategory())
                .write_at(formatted)
                .file_url(documentEntity.getFile_url())
                .department_name(documentEntity.getDepartmentName())
                .build();
    }
}
