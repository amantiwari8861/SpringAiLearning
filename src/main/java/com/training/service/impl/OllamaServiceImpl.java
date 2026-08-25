package com.training.service.impl;

import com.training.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class OllamaServiceImpl implements ChatService {
    private final ChatClient chatClient;

    public OllamaServiceImpl(@Qualifier("ollamaChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String chat(String message) {

        return chatClient
                .prompt()
                .system("""
                    You are a professional Java and Spring Boot assistant.
                    Give technically accurate answers.
                    Prefer production-ready approaches.
                    Explain complex concepts with practical examples.
                    """)
                .user(message)
                .call()
                .content();
    }
    @Override
    public Flux<String> stream(String message) {
        return chatClient
                .prompt()
                .user(message)
                .stream()
                .content();
    }
}
