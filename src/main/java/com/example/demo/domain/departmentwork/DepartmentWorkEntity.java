package com.example.demo.domain.departmentwork;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "department_work")
public class DepartmentWorkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_id")
    private Long workId;

    @Column(name = "work_name")
    private String workName;

    @Column(name = "work_begin_date")
    private LocalDate startDate;

    @Column(name = "work_end_date")
    private LocalDate endDate;

    @Column(name = "work_agree")
    private Boolean isPublic;

    @Column(name = "work_open_begin_date")
    private LocalDate openStartDate;

    @Column(name = "work_open_end_date")
    private LocalDate openEndDate;

    @Column(name = "work_manager")
    private String workManager;

    @Column(name = "linked_unit_work")
    private String linkedUnitWork;

    @Column(name = "work_alarm_setting")
    private String alarmSetting;

    @Column(name = "work_attachment")
    private String attachmentPath;

    @Column(name = "work_category")
    private String category;

    @Column(name = "department_id", nullable = false)
    private Long departmentId;
}