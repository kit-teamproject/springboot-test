package com.example.demo.domain.departmentwork;

import com.example.demo.domain.departmentwork.dto.ReqDepartmentWorkForm;
import com.example.demo.domain.departmentwork.dto.ResDepartmentWorkDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentWorkService {

    private final DepartmentWorkRepository workRepository;

    public void registerWork(ReqDepartmentWorkForm form) throws IOException {
        DepartmentWorkEntity work = new DepartmentWorkEntity();

        work.setWorkName(form.getWorkName());
        work.setStartDate(form.getStartDate());
        work.setEndDate(form.getEndDate());
        work.setIsPublic(form.getIsPublic());
        work.setLinkedUnitWork(form.getLinkedUnitWork());
        work.setDepartmentId(form.getDepartmentId());
        work.setCategory(form.getCategory());
        work.setWorkManager(form.getWorkManager());

        // 알림 여부 처리 (Y/N)
        Boolean alarm = form.getAlarmSetting();
        work.setAlarmSetting((alarm != null && alarm) ? "Y" : "N");

        // 공개일자 설정
        if (Boolean.TRUE.equals(form.getIsPublic())) {
            work.setOpenStartDate(form.getOpenStartDate());
            work.setOpenEndDate(form.getOpenEndDate());
        }

        // 파일 이름만 저장
        MultipartFile file = form.getAttachment();
        if (file != null && !file.isEmpty()) {
            work.setAttachmentPath(file.getOriginalFilename());
        }

        workRepository.save(work);
    }

    public List<ResDepartmentWorkDto> searchWork(String workName, String managerName) {
        List<DepartmentWorkEntity> works;

        boolean hasWorkName = workName != null && !workName.isBlank();
        boolean hasManager = managerName != null && !managerName.isBlank();

        if (hasWorkName && hasManager) {
            works = workRepository.findByWorkNameContainingAndWorkManagerContaining(workName, managerName);
        } else if (hasWorkName) {
            works = workRepository.findByWorkNameContaining(workName);
        } else if (hasManager) {
            works = workRepository.findByWorkManagerContaining(managerName);
        } else {
            works = workRepository.findAll();
        }

        return works.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private ResDepartmentWorkDto toDto(DepartmentWorkEntity e) {
        return new ResDepartmentWorkDto(
                e.getWorkId(),
                e.getWorkName(),
                e.getStartDate(),
                e.getEndDate(),
                e.getWorkManager(),
                e.getIsPublic(),
                e.getCategory(),
                Boolean.valueOf("Y".equals(e.getAlarmSetting())),
                e.getLinkedUnitWork(),
                e.getAttachmentPath(),
                e.getDepartmentId()
        );
    }
}
