package com.example.demo.domain.schedule;

import com.example.demo.domain.schedule.dto.ReqScheduleForm;
import com.example.demo.domain.schedule.dto.ResScheduleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 일정(Schedule) 관련 요청을 처리하는 컨트롤러
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/schedule") // 기본 URL 경로 설정
public class ScheduleController {

    private final ScheduleService scheduleService; // 일정 관련 비즈니스 로직을 처리하는 서비스

    /**
     * 일정 목록 조회
     * /schedule/list 요청 처리
     *
     * @param keyword 검색어 (일정명)
     * @param startDate 조회 시작일
     * @param endDate 조회 종료일
     * @param model View로 데이터를 전달하기 위한 객체
     * @return 일정 목록을 출력하는 뷰 이름
     */
    @GetMapping("/list")
    public String index(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate,
            Model model) {

        List<ResScheduleDto> schedules = scheduleService.getScheduleList(keyword, startDate, endDate);

        // View로 전달할 데이터 추가
        model.addAttribute("list", schedules);      // 일정 리스트
        model.addAttribute("keyword", keyword);     // 검색어 유지
        model.addAttribute("startDate", startDate); // 시작일 유지
        model.addAttribute("endDate", endDate);     // 종료일 유지

        return "scheduleManagement/scheduleInquiry"; // 렌더링할 템플릿 이름
    }

    /**
     * 일정 등록 화면 요청
     * /schedule/add (GET) 요청 처리
     *
     * @param model View로 폼 객체 전달
     * @return 등록 폼 페이지 템플릿 이름
     */
    @GetMapping("/add")
    public String add(Model model) {
        // 빈 폼 객체를 뷰에 전달
        model.addAttribute("ReqScheduleForm", new ReqScheduleForm());
        return "scheduleManagement/addSchedule";
    }

    /**
     * 일정 등록 요청 처리
     * /schedule/add (POST) 요청 처리
     *
     * @param form 사용자가 입력한 일정 등록 정보
     * @return 등록 후 목록 페이지로 리다이렉트
     */
    @PostMapping("/add")
    public String addSchedule(@ModelAttribute ReqScheduleForm form) {
        // 작성자 ID는 임시로 고정 (로그인 연동 시 교체 예정)
        Long writerId = 1L; // 예: 임시 직원 ID

        // 서비스 호출하여 일정 등록
        scheduleService.createSchedule(form, writerId);

        // 등록 후 목록 페이지로 이동
        return "redirect:/schedule/list";
    }
}
