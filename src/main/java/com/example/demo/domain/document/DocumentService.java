package com.example.demo.domain.document;

import com.example.demo.domain.document.dto.ReqDocumentAddFormDto;
import com.example.demo.domain.document.dto.ResDocumentDto;
import com.example.demo.domain.employee.EmployeeEntity;
import com.example.demo.domain.employee.EmployeeRepository;
import com.example.demo.domain.learningclub.LearningClubEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final EmployeeRepository employeeRepository;

    public List<ResDocumentDto> getDocumentList() {
        List<DocumentEntity> list = documentRepository.findAllByOrderByIdDesc()
                .orElse(new ArrayList<>());

        return list.stream().map(ResDocumentDto::documentEntityToDto).toList();
    }

    public void addDocument(ReqDocumentAddFormDto reqDocumentAddFormDto) {
        try {
            String filePathUrl = null;
            MultipartFile file = reqDocumentAddFormDto.getAttachment();

            if (file != null && !file.isEmpty()) {
                // 파일 저장 경로
                String uploadDir = "/app/uploads/files/"; // ✅ 변경된 경로

                String originalFilename = file.getOriginalFilename();

                String ext = "";
                if (originalFilename != null && originalFilename.contains(".")) {
                    ext = originalFilename.substring(originalFilename.lastIndexOf("."));
                }

                String newFileName = UUID.randomUUID().toString() + ext;

                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path fullPath = uploadPath.resolve(newFileName);
                file.transferTo(fullPath.toFile());

                filePathUrl = "/files/" + newFileName;
            }

            EmployeeEntity creator = employeeRepository.findByUserid(reqDocumentAddFormDto.getEmp_userid())
                    .orElseThrow(() -> new RuntimeException("생성자 정보를 찾을 수 없습니다."));

            List<String> categories = reqDocumentAddFormDto.getCategories();
            String joined = (categories != null) ? String.join(",", categories) : "";


            DocumentEntity doc = new DocumentEntity();
            doc.setTitle(reqDocumentAddFormDto.getTitle());
            doc.setContents(reqDocumentAddFormDto.getDescription());
            doc.setFile_url(filePathUrl);
            doc.setWrite_at(LocalDateTime.now());
            doc.setCategory(joined);
            doc.setDepartmentName(creator.getDepartment());
            doc.setEmployee(creator);

            documentRepository.save(doc);

        } catch (Exception e) {
            throw new RuntimeException("문서 등록 중 오류 발생", e);
        }

    }

    public List<ResDocumentDto> findByCategory(String keyword) {
        return documentRepository.findAllByCategoryContainingIgnoreCaseOrderByIdDesc(keyword)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(ResDocumentDto::documentEntityToDto)
                .toList();
    }

    public List<ResDocumentDto> findByTitle(String keyword) {
        return documentRepository.findAllByTitleContainingIgnoreCaseOrderByIdDesc(keyword)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(ResDocumentDto::documentEntityToDto)
                .toList();
    }

    public List<ResDocumentDto> findByDepartment(String keyword) {
        return documentRepository.findAllByDepartmentNameContainingIgnoreCaseOrderByIdDesc(keyword)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(ResDocumentDto::documentEntityToDto)
                .toList();
    }

}
