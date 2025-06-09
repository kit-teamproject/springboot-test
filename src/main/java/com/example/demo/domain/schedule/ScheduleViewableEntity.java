package com.example.demo.domain.schedule;

import com.example.demo.domain.employee.EmployeeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "schedule_viewable")
public class ScheduleViewableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // PK 컬럼, ERD에는 PK 없으니 추가 필요 (JPA는 반드시 PK 필요) 변경사항
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private ScheduleEntity schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "viewable_employee_id")
    private EmployeeEntity viewableEmployee;
}
