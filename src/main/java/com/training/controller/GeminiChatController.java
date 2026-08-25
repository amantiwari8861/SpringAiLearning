package com.training.controller;

import com.training.service.ChatService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat/gemini")
public class GeminiChatController {

    private final ChatService chatService;

    public GeminiChatController(@Qualifier("geminiServiceImpl") ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public String chat(@RequestParam String message) {
        return chatService.chat(message);
    }
}