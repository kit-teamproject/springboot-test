package com.example.demo.domain.schedule.dto;

import com.example.demo.domain.employee.EmployeeEntity;
import com.example.demo.domain.schedule.ScheduleEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ResScheduleDto {
    private Long scheduleId;

    private String scheduleName;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean shareYn;

    private Integer importance;

    private LocalDateTime registrationDatetime;

    private Boolean sendSmsYn;

    private EmployeeDto writer; // 작성자 정보

    @Getter
    @Setter
    @Builder
    public static class EmployeeDto {
        private String userid;

        public static EmployeeDto employeeEntityToDto(EmployeeEntity employeeEntity) {
            return EmployeeDto.builder()
                    .userid(employeeEntity.getUserid())
                    .build();
        }
    }

    public static ResScheduleDto scheduleEntityToDto(ScheduleEntity scheduleEntity) {
        return ResScheduleDto.builder()
                .scheduleId(scheduleEntity.getScheduleId())
                .scheduleName(scheduleEntity.getScheduleName())
                .startDate(scheduleEntity.getStartDate())
                .endDate(scheduleEntity.getEndDate())
                .shareYn(scheduleEntity.getShareYn())
//                .importance(scheduleEntity.getImportance())
                .registrationDatetime(scheduleEntity.getRegistrationDatetime())
//                .sendSmsYn(scheduleEntity.getSendSmsYn())
                .writer(EmployeeDto.employeeEntityToDto(scheduleEntity.getWriter()))
                .build();
    }

}
