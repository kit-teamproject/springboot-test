package com.example.demo.domain.document;

import com.example.demo.domain.document.dto.ReqDocumentAddFormDto;
import com.example.demo.domain.document.dto.ResDocumentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping("/list")
    public String index(@RequestParam(required = false) String type, @RequestParam(required = false) String keyword, Model model) {

        List<ResDocumentDto> list;

        if (type != null && keyword != null && !keyword.isBlank()) {
            if ("category".equals(type)) {
                list = documentService.findByCategory(keyword);
            } else if ("title".equals(type)) {
                list = documentService.findByTitle(keyword);
            } else if ("department".equals(type)) {
                list = documentService.findByDepartment(keyword);
            } else {
                list = documentService.getDocumentList();
            }
        } else {
            list = documentService.getDocumentList();
        }

        model.addAttribute("documents", list);
        model.addAttribute("searchType", type);
        model.addAttribute("searchKeyword", keyword);
        return "document/document_list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("form", new ReqDocumentAddFormDto());
        return "document/document_add";
    }

    @PostMapping("/create")
    public String createDocument(@ModelAttribute ReqDocumentAddFormDto formDto) {
        documentService.addDocument(formDto);
        return "redirect:/document/list";
    }

    @GetMapping("/test")
    @ResponseBody
    public List<ResDocumentDto> test() {
        return documentService.getDocumentList();
    }
}
