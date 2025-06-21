package com.example.demo.domain.learningclub;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 학습 동아리(LearningClubEntity)에 대한 데이터베이스 접근을 담당하는 Repository 인터페이스
 * JpaRepository를 상속받아 기본적인 CRUD 기능을 자동으로 제공받음
 */
@Repository // 이 인터페이스가 스프링의 Repository 컴포넌트임을 나타냄 (생략 가능)
public interface LearningClubRepository extends JpaRepository<LearningClubEntity, Long> {
    // JpaRepository<엔티티 타입, 기본키 타입>

    /**
     * 학습 동아리 목록을 ID 기준으로 내림차순 정렬해서 전체 조회
     *
     * @return 학습 동아리 목록 (Optional로 감쌈)
     */
    Optional<List<LearningClubEntity>> findAllByOrderByLearningClubIdDesc();

    /**
     * 동아리 이름에 특정 문자열이 포함된 항목을 대소문자 구분 없이 검색
     *
     * @param clubName 검색할 동아리 이름 일부
     * @return 검색된 학습 동아리 목록 (Optional로 감쌈)
     */
    Optional<List<LearningClubEntity>> findByClubNameContainingIgnoreCase(String clubName);

    Boolean existsByClubName(String clubName);
}
