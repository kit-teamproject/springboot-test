package com.example.demo.domain.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
    Optional<List<DocumentEntity>> findAllByOrderByIdDesc();

    Optional<List<DocumentEntity>> findAllByCategoryContainingIgnoreCaseOrderByIdDesc(String word);

    Optional<List<DocumentEntity>> findAllByTitleContainingIgnoreCaseOrderByIdDesc(String word);

    Optional<List<DocumentEntity>> findAllByDepartmentNameContainingIgnoreCaseOrderByIdDesc(String word);
}
