package com.training.controller;

import com.training.service.impl.VectorStoreService;
import org.springframework.ai.document.Document;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vector")
public class VectorStoreController {

    private final VectorStoreService vectorStoreService;

    public VectorStoreController(VectorStoreService vectorStoreService) {
        this.vectorStoreService = vectorStoreService;
    }

    @PostMapping("/documents")
    public String addDocuments() {

        vectorStoreService.addDocuments();

        return "Documents added";
    }

    @GetMapping("/search")
    public List<Document> search(
            @RequestParam String query) {

        return vectorStoreService.search(query);
    }
}