package com.example.demo.domain.learningclub;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "learning_club")
public class LearningClubEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long learning_club_id;

    private String club_name;
    private String intro;
    private String img_url;
}
