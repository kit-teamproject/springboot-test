package com.example.demo.domain.departmentwork;

import com.example.demo.domain.departmentwork.DepartmentWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkRepository extends JpaRepository<DepartmentWorkEntity, Long> {

    // 업무명 또는 담당자 ID 기반 검색용 메서드 (optional)
    List<WorkEntity> findByWorkNameContainingAndWorkManager_EmployeeId(String workName, Long employeeId);

    // 업무명만으로 검색
    List<WorkEntity> findByWorkNameContaining(String workName);

    // 담당자만으로 검색
    List<WorkEntity> findByWorkManager_EmployeeId(Long employeeId);
}
