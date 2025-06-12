package com.example.demo.domain.learningclub;

import com.example.demo.domain.employee.EmployeeEntity;
import com.example.demo.domain.employee.EmployeeRepository;
import com.example.demo.domain.learningclub.dto.ReqLearningClubForm;
import com.example.demo.domain.learningclub.dto.ResLearningClubDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class  LearningClubService {
    private final LearningClubRepository learningClubRepository;
    private final EmployeeRepository employeeRepository; // 생성자 정보 가져올 때 사용


    public List<ResLearningClubDto> getLearningClubList() {
        List<LearningClubEntity> learningClubEntityList = learningClubRepository.findAllByOrderByLearningClubIdDesc()
                .orElseThrow(() -> new RuntimeException("조회 결과 없음"));

        List<ResLearningClubDto> resLearningClubDtoList = learningClubEntityList
                .stream()
                .map(ResLearningClubDto::learningClubEntityToDto)
                .collect(Collectors.toList());

        return resLearningClubDtoList;
    }

    public List<ResLearningClubDto> getSearchLearningClubByName(String keyword) {
        List<LearningClubEntity> learningClubEntityList = learningClubRepository
                .findByClubNameContainingIgnoreCase(keyword)
                .orElseThrow(() -> new RuntimeException("검색 결과가 없습니다."));

        List<ResLearningClubDto> resLearningClubDtoList = learningClubEntityList
                .stream()
                .map(ResLearningClubDto::learningClubEntityToDto)
                .collect(Collectors.toList());

        return resLearningClubDtoList;
    }

    public void createLearningClub(ReqLearningClubForm form, String creatorId) {
        try {
            String imagePath = null;
            MultipartFile image = form.getImage();
            if (image != null && !image.isEmpty()) {
                //로컬
//                String uploadDir = "C:/Users/USER/Desktop/assets/";

                //서버
                String uploadDir = "/app/uploads/assets/";

                String originalFilename = image.getOriginalFilename();

                String ext = "";
                if (originalFilename != null && originalFilename.contains(".")) {
                    ext = originalFilename.substring(originalFilename.lastIndexOf("."));
                }

                String newFileName = UUID.randomUUID().toString() + ext;

                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(newFileName);
                image.transferTo(filePath.toFile());

                imagePath = "/assets/" + newFileName;
            } else {
                imagePath = "/assets/image.webp";
            }

            EmployeeEntity creator = employeeRepository.findByUserid(creatorId)
                    .orElseThrow(() -> new RuntimeException("생성자 정보를 찾을 수 없습니다."));

            LearningClubEntity club = new LearningClubEntity();
            club.setClubName(form.getName());
            club.setIntro(form.getDescription());
            club.setImg_url(imagePath);
            club.setEmployeeEntity(creator);

            learningClubRepository.save(club);

        } catch (Exception e) {
            throw new RuntimeException("동아리 생성 중 오류 발생", e);
        }

    }


}
