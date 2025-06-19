package com.example.demo.domain.departmentwork;

import com.example.demo.domain.employee.EmployeeEntity;
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

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "is_public")
    private Boolean isPublic;

    @Column(name = "open_start_date")
    private LocalDate openStartDate;

    @Column(name = "open_end_date")
    private LocalDate openEndDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id") // 외래키, EmployeeEntity 참조
    private EmployeeEntity workManager;

    @Column(name = "linked_unit_work")
    private String linkedUnitWork;

    @Column(name = "alarm_setting")
    private Boolean alarmSetting;

    @Lob
    @Column(name = "work_attachment")
    private byte[] workAttachment;

    @Column(name = "work_category")
    private String category;
}
