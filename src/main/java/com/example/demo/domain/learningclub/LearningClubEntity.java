package com.example.demo.domain.learningclub;

import com.example.demo.domain.employee.EmployeeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * 학습 동아리 정보를 저장하는 JPA 엔티티 클래스
 * 이 클래스는 데이터베이스의 'learning_club' 테이블과 매핑됨
 */
@Entity // JPA에서 엔티티 클래스임을 나타냄 (테이블과 매핑)
@Getter // Lombok을 사용하여 getter 메서드 자동 생성
@Setter // Lombok을 사용하여 setter 메서드 자동 생성
@Table(name = "learning_club") // 매핑할 테이블 이름 지정
public class LearningClubEntity {

    @Id // 기본 키(primary key) 필드임을 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 자동 증가 전략을 사용 (MySQL의 AUTO_INCREMENT)
    @Column(name = "learning_club_id") // 매핑할 컬럼 이름 지정
    private Long learningClubId;

    @Column(name = "club_name") // 'club_name' 컬럼과 매핑
    private String clubName;

    // 소개 (소개글 컬럼, 컬럼 이름 생략 시 변수 이름과 동일한 컬럼에 매핑됨)
    private String intro;

    // 이미지 URL (동아리 이미지 경로 또는 링크)
    private String img_url;

    @ManyToOne(fetch = FetchType.LAZY)
    // 여러 동아리가 하나의 직원(동아리장)을 가질 수 있으므로 ManyToOne 관계
    // LAZY: 실제로 필요한 시점에만 연관 객체를 로딩 (성능 최적화)
    @JoinColumn(name = "club_head_id")
    // 이 엔티티의 'club_head_id' 컬럼이 외래 키 역할을 하며, employee 테이블의 기본 키를 참조
    private EmployeeEntity employeeEntity;
}
