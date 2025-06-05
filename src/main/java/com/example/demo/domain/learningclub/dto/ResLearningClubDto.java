package com.example.demo.domain.learningclub.dto;

import com.example.demo.domain.employee.EmployeeEntity;
import com.example.demo.domain.learningclub.LearningClubEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResLearningClubDto {
    private Long learning_club_id;
    private String clubName;
    private String intro;
    private String img_url;
    private EmployeeDto employeeDto;


    @Getter
    @Setter
    @Builder
    public static class EmployeeDto {
        private String userid;
        private String role;
        private String department;

        public static EmployeeDto employeeEntityToDto(EmployeeEntity employeeEntity) {
            return EmployeeDto.builder()
                    .userid(employeeEntity.getUserid())
                    .role(employeeEntity.getRole())
                    .department(employeeEntity.getDepartment())
                    .build();
        }
    }

    public static ResLearningClubDto learningClubEntityToDto(LearningClubEntity learningClubEntity) {
        return ResLearningClubDto.builder()
                .learning_club_id(learningClubEntity.getLearningClubId())
                .clubName(learningClubEntity.getClubName())
                .intro(learningClubEntity.getIntro())
                .img_url(learningClubEntity.getImg_url())
                .employeeDto(EmployeeDto.employeeEntityToDto(learningClubEntity.getEmployeeEntity()))
                .build();
    }
}
