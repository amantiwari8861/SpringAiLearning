package com.training.controller;

import com.training.dto.ChatRequest;
import com.training.service.ChatService;
import com.training.service.impl.SentimentalAnalysis;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/openai")
public class OpenAiChatController {

    @Autowired
    private SentimentalAnalysis sentimentService;
    private final ChatService chatService;

    public OpenAiChatController(@Qualifier("openAIServiceImpl") ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatService.chat(message);
    }
    @PostMapping("/chat")
    public String chat2(@RequestBody @Valid ChatRequest request,@CookieValue(value = "conversationId", required = false) String conversationId, HttpServletResponse response) {
        if (conversationId == null || conversationId.isBlank()) {

            conversationId = UUID.randomUUID().toString();

            ResponseCookie cookie = ResponseCookie
                    .from("conversationId", conversationId)
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("Lax")
                    .path("/")
                    .maxAge(Duration.ofDays(30))
                    .build();

            response.addHeader(
                    HttpHeaders.SET_COOKIE,
                    cookie.toString()
            );
        }
        return chatService.chat(conversationId,request.message());
    }

    @PostMapping(path="/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> stream(@RequestBody @Valid ChatRequest request) {
        return chatService.stream(request.message());
    }
    @PostMapping("/sentimental-analysis")
    public ResponseEntity<String> analyseSentiment(@RequestBody @Valid ChatRequest request) {
        return ResponseEntity.ok(sentimentService.analyse(request.message()));
    }
    @PostMapping("/generate-image")
    public ResponseEntity<String> generateImage(@RequestBody @Valid ChatRequest request) {
        return ResponseEntity.status(201).body(chatService.generateImage(request.message()));
    }

}