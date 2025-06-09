package com.example.demo.domain.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 일정 조회 권한(ScheduleViewableEntity)에 대한 Repository 인터페이스
 * 기본 CRUD 기능 제공
 */
@Repository
public interface ScheduleViewableRepository extends JpaRepository<ScheduleViewableEntity, Long> {
    // JpaRepository<엔티티 타입, 기본키 타입>
}