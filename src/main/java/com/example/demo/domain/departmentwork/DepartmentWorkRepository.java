package com.example.demo.domain.departmentwork;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DepartmentWorkRepository extends JpaRepository<DepartmentWorkEntity, Long> {

    @Query("SELECT w FROM DepartmentWorkEntity w " +
           "WHERE (:workName IS NULL OR w.workName LIKE %:workName%) " +
           "AND (:workManager IS NULL OR w.workManager LIKE %:workManager%) " +
           "AND (:startDate IS NULL OR w.startDate >= :startDate) " +
           "AND (:endDate IS NULL OR w.endDate <= :endDate)")
    List<DepartmentWorkEntity> searchWorks(@Param("workName") String workName,
                                           @Param("workManager") String workManager,
                                           @Param("startDate") LocalDate startDate,
                                           @Param("endDate") LocalDate endDate);
}