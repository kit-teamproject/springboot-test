package com.example.demo.domain.schedule;

import com.example.demo.domain.employee.EmployeeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "schedule")
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id") // 기본 키 컬럼명
    private Long scheduleId;

    @Column(name = "schedule_name") // 일정 제목
    private String scheduleName;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "date_length")
    private Integer dateLength;

    @Column(name = "is_share")
    private Boolean shareYn;

//    @Column(name = "importance")
//    private Integer importance; // 중요도 설정

    @Column(name = "created_at")
    private LocalDateTime registrationDatetime;

//    @Column(name = "send_sms_yn")
//    private Boolean sendSmsYn; // SDD 기능 반영 (알림 전송 여부)

    @ManyToOne(fetch = FetchType.LAZY)
    // 여러 일정이 하나의 직원(작성자)을 가질 수 있으므로 ManyToOne 관계
    @JoinColumn(name = "uploader_id")
    // 이 엔티티의 'writer_id' 컬럼이 외래 키 역할을 하며, employee 테이블의 기본 키를 참조
    private EmployeeEntity writer;
}
