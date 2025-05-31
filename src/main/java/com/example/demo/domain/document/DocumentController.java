package com.example.demo.domain.document;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/document")
public class DocumentController {
    @GetMapping("/list")
    public String index() {
        return "document/document_list";
    }

    @GetMapping("/add")
    public String add() {
        return "document/document_add";
    }
}
