package com.example.demo.domain.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    /**
     * 일정 목록을 ID 기준으로 내림차순 정렬해서 전체 조회
     *
     * @return 일정 목록 (Optional로 감쌈)
     */
    Optional<List<ScheduleEntity>> findAllByOrderByScheduleIdDesc();

    /**
     * 일정명에 특정 문자열이 포함된 항목을 대소문자 구분 없이 검색
     *
     * @param scheduleName 검색할 일정명 일부
     * @return 검색된 일정 목록 (Optional로 감쌈)
     */
    Optional<List<ScheduleEntity>> findByScheduleNameContainingIgnoreCase(String scheduleName);

    /**
     * 키워드 + 기간 검색
     * - 키워드는 scheduleName LIKE 검색
     * - 시작일 ~ 종료일 기간 검색 (startDate ~ endDate)
     */
    @Query("SELECT s FROM ScheduleEntity s " +
            "WHERE (:keyword IS NULL OR LOWER(s.scheduleName) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:startDate IS NULL OR s.startDate >= :startDate) " +
            "AND (:endDate IS NULL OR s.endDate <= :endDate) " +
            "ORDER BY s.scheduleId DESC")
    Optional<List<ScheduleEntity>> findScheduleList(
            @Param("keyword") String keyword,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
