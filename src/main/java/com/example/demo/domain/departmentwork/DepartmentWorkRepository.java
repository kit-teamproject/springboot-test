package com.example.demo.domain.departmentwork;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DepartmentWorkRepository extends JpaRepository<DepartmentWorkEntity, Long> {

    // 업무명, 담당자 이름 모두 부분 일치 검색
    List<DepartmentWorkEntity> findByWorkNameContainingAndWorkManagerContaining(String workName, String workManager);

    // 업무명만 부분 일치 검색
    List<DepartmentWorkEntity> findByWorkNameContaining(String workName);

    // 담당자만 부분 일치 검색
    List<DepartmentWorkEntity> findByWorkManagerContaining(String workManager);
}
