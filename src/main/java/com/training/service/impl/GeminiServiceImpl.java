package com.training.service.impl;

import com.training.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GeminiServiceImpl implements ChatService {

    private final ChatClient chatClient;

    public GeminiServiceImpl(@Qualifier("geminiChatClient") ChatClient chatClient) {
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
    public String explain(String topic) {

        return chatClient
                .prompt()
                .system("""
                    You are a Java instructor.
                    Explain concepts clearly for developers.
                    """)
                .user("""
                    Explain the following topic:

                    {topic}

                    Include:
                    1. Definition
                    2. How it works
                    3. Practical example
                    4. Common mistakes
                    """)
                .call()
                .content();
    }
}