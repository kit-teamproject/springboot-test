package com.example.demo.domain.schedule;

import com.example.demo.domain.employee.EmployeeEntity;
import com.example.demo.domain.employee.EmployeeRepository;
import com.example.demo.domain.schedule.dto.ReqScheduleForm;
import com.example.demo.domain.schedule.dto.ResScheduleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 일정(Schedule) 관련 비즈니스 로직을 처리하는 서비스 클래스
 */
@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleViewableRepository scheduleViewableRepository;
    private final EmployeeRepository employeeRepository;

    /**
     * 일정 목록 조회
     *
     * @param keyword   검색어 (일정명)
     * @param startDate 조회 시작일 (yyyy-MM-dd 형식)
     * @param endDate   조회 종료일 (yyyy-MM-dd 형식)
     * @return 일정 목록 (ResScheduleDto 리스트)
     */
    @Transactional(readOnly = true)
    public List<ResScheduleDto> getScheduleList(String keyword, String startDate, String endDate) {

        // 날짜 파라미터 변환
        LocalDate start = (startDate != null && !startDate.isEmpty()) ? LocalDate.parse(startDate) : null;
        LocalDate end = (endDate != null && !endDate.isEmpty()) ? LocalDate.parse(endDate) : null;

        // 일정 조회
        List<ScheduleEntity> scheduleEntityList = scheduleRepository.findScheduleList(keyword, start, end)
                .orElseThrow(() -> new RuntimeException("일정 조회 결과 없음"));

        // DTO 변환 후 반환
        return scheduleEntityList.stream()
                .map(ResScheduleDto::scheduleEntityToDto)
                .collect(Collectors.toList());
    }

    /**
     * 일정 등록
     *
     * @param form     일정 등록 폼 데이터
     * @param writerId 작성자(직원) ID
     */
    @Transactional
    public void createSchedule(ReqScheduleForm form, Long writerId) {
        try {
            // 작성자 조회
            EmployeeEntity writer = employeeRepository.findById(writerId)
                    .orElseThrow(() -> new RuntimeException("작성자 정보를 찾을 수 없습니다."));

            // ScheduleEntity 생성
            ScheduleEntity schedule = new ScheduleEntity();
            schedule.setScheduleName(form.getScheduleName());
            schedule.setStartDate(LocalDate.parse(form.getStartDate()));
            schedule.setEndDate(LocalDate.parse(form.getEndDate()));
            // 자동 기간 계산
            int period = (int) (schedule.getEndDate().toEpochDay() - schedule.getStartDate().toEpochDay()) + 1;
            schedule.setDateLength(period);
            schedule.setShareYn(Boolean.parseBoolean(form.getShareYn()));
//            schedule.setImportance(form.getImportance());
            schedule.setRegistrationDatetime(LocalDateTime.now());
//            schedule.setSendSmsYn(Boolean.parseBoolean(form.getSendSmsYn()));
            schedule.setWriter(writer);

            // 일정 저장
            ScheduleEntity savedSchedule = scheduleRepository.save(schedule);

            // 공유 대상자 처리
            saveScheduleViewable(savedSchedule, form.getViewableEmployeeIds());

        } catch (Exception e) {
            throw new RuntimeException("일정 생성 중 오류 발생", e);
        }
    }

    /**
     * 공유 대상자 정보 저장
     *
     * @param schedule             저장된 일정 엔티티
     * @param viewableEmployeeIds  콤마(,) 구분 문자열
     */
    private void saveScheduleViewable(ScheduleEntity schedule, String viewableEmployeeIds) {

        if (viewableEmployeeIds != null && !viewableEmployeeIds.isEmpty()) {
            List<String> employeeIdList = Arrays.asList(viewableEmployeeIds.split(","));

            for (String empIdStr : employeeIdList) {
                Long empId = Long.parseLong(empIdStr.trim());

                EmployeeEntity viewableEmployee = employeeRepository.findById(empId)
                        .orElseThrow(() -> new RuntimeException("공유 대상자 정보가 없습니다. ID: " + empId));

                ScheduleViewableEntity viewableEntity = new ScheduleViewableEntity();
                viewableEntity.setSchedule(schedule);
                viewableEntity.setViewableEmployee(viewableEmployee);

                scheduleViewableRepository.save(viewableEntity);
            }
        }
    }
}
