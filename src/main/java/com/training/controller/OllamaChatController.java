package com.training.controller;

import com.training.dto.ChatRequest;
import com.training.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/chat/ollama")
public class OllamaChatController {

    private final ChatService chatService;

    public OllamaChatController(@Qualifier("ollamaServiceImpl") ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping
    public String chat(@RequestParam String message) {
        return chatService.chat(message);
    }

    @PostMapping(path="/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(@RequestBody @Valid ChatRequest request) {
        return chatService.stream(request.message());
    }

}