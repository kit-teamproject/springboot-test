package com.example.demo.domain.departmentwork;

import com.example.demo.domain.employee.EmployeeEntity;
import com.example.demo.domain.department.dto.ReqDepartmentWorkForm;
import com.example.demo.domain.department.dto.ResDepartmentWorkForm;
import com.example.demo.domain.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentWorkService {

    private final DepartmentWorkRepository workRepository;
    private final EmployeeRepository employeeRepository;

    public void registerWork(ReqDepartmentWorkForm form) throws IOException {
        DepartmentWorkEntity work = new DepartmentWorkEntity();

        work.setWorkName(form.getWorkName());
        work.setStartDate(form.getStartDate());
        work.setEndDate(form.getEndDate());
        work.setIsPublic(form.getIsPublic());
        work.setLinkedUnitWork(form.getLinkedUnitWork());
        work.setAlarmSetting(form.getAlarmSetting());
        work.setCategory(form.getCategory());

        // 공개 여부에 따라 공개 기간 설정
        if (Boolean.TRUE.equals(form.getIsPublic())) {
            work.setOpenStartDate(form.getOpenStartDate());
            work.setOpenEndDate(form.getOpenEndDate());
        } else {
            work.setOpenStartDate(null);
            work.setOpenEndDate(null);
        }

        // 담당자 연결
        EmployeeEntity manager = employeeRepository.findById(form.getManagerId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 담당자 ID입니다."));
        work.setWorkManager(manager);

        // 파일 저장
        MultipartFile file = form.getAttachment();
        if (file != null && !file.isEmpty()) {
            work.setWorkAttachment(file.getBytes()); // 파일 내용을 byte[]로 읽어서 저장
        }

        workRepository.save(work);
    }

    public List<ResDepartmentWorkForm> searchWork(String workName, Long managerId) {
        List<DepartmentWorkEntity> works;

        if (workName != null && !workName.isBlank() && managerId != null) {
            works = workRepository.findByWorkNameContainingAndWorkManager_EmployeeId(workName, managerId);
        } else if (workName != null && !workName.isBlank()) {
            works = workRepository.findByWorkNameContaining(workName);
        } else if (managerId != null) {
            works = workRepository.findByWorkManager_EmployeeId(managerId);
        } else {
            works = workRepository.findAll();
        }

        return works.stream()
                .map(e -> new ResDepartmentWorkForm(
                        e.getWorkId(),
                        e.getWorkName(),
                        e.getStartDate(),
                        e.getEndDate(),
                        e.getWorkManager().getName(),
                        e.getIsPublic(),
                        e.getCategory()))
                .collect(Collectors.toList());
    }
}
