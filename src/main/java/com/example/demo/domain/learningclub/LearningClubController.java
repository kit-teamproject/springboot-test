package com.example.demo.domain.learningclub;

import com.example.demo.domain.learningclub.dto.ReqLearningClubForm;
import com.example.demo.domain.learningclub.dto.ResLearningClubDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 학습 동아리 관련 요청을 처리하는 컨트롤러
 */
@Controller // 이 클래스가 Spring MVC에서 컨트롤러 역할을 함을 나타냄
@RequiredArgsConstructor // final 필드에 대한 생성자를 자동으로 생성해줌 (DI 용도)
@RequestMapping("/learning_club/") // 이 컨트롤러에서 처리할 기본 URL 경로 설정
public class LearningClubController {

    private final LearningClubService learningClubService; // 학습 동아리 관련 비즈니스 로직을 처리하는 서비스

    /**
     * 학습 동아리 목록 조회
     * /learning_club/list 요청을 처리
     *
     * @param query 검색어 (선택값)
     * @param model View로 데이터를 전달하기 위한 객체
     * @return 학습 동아리 목록을 출력하는 뷰 이름
     */
    @GetMapping("/list")
    public String index(@RequestParam(value = "query", required = false) String query, Model model) {
        List<ResLearningClubDto> clubs;

        // 검색어가 없으면 전체 목록 조회
        if (query == null || query.isEmpty()) {
            clubs = learningClubService.getLearningClubList();
        } else {
            // 검색어가 있으면 이름으로 검색
            clubs = learningClubService.getSearchLearningClubByName(query);
        }

        // View로 전달할 데이터 추가
        model.addAttribute("list", clubs);   // 학습 동아리 리스트
        model.addAttribute("query", query);  // 검색어 유지
        return "learning_club/learning_club_list"; // 렌더링할 템플릿 이름
    }

    /**
     * 학습 동아리 등록 화면 요청
     * /learning_club/add (GET) 요청을 처리
     *
     * @param model View로 폼 객체 전달
     * @return 등록 폼 페이지 템플릿 이름
     */
    @GetMapping("/add")
    public String add(Model model) {
        // 빈 폼 객체를 뷰에 전달
        model.addAttribute("ReqLearningClubForm", new ReqLearningClubForm());
        return "learning_club/learning_club_add";
    }

    /**
     * 학습 동아리 등록 요청 처리
     * /learning_club/add (POST) 요청을 처리
     *
     * @param form 사용자가 입력한 동아리 등록 정보
     * @return 등록 후 목록 페이지로 리다이렉트
     */
    @PostMapping("/add")
    public String addLearningClub(@ModelAttribute ReqLearningClubForm form) {
        // 생성자 ID는 임시로 고정 -> 세션스토리지에서 get
        String creatorId = form.getEmp_userid();

        System.out.println(form.getEmp_userid());
        // 서비스 호출하여 동아리 등록
        learningClubService.createLearningClub(form, creatorId);

        // 등록 후 목록 페이지로 이동
        return "redirect:/learning_club/list";
    }
}
