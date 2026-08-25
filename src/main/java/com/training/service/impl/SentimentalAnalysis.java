package com.training.service.impl;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SentimentalAnalysis {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/sentimental.st")
    private Resource sentimentPrompt;

    public SentimentalAnalysis(@Qualifier("openAiChatClient")ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String analyse(String text) {

        SystemPromptTemplate systemPromptTemplate =
                new SystemPromptTemplate(sentimentPrompt);

        String systemPrompt = systemPromptTemplate.render(
                Map.of("text", text)
        );
        System.out.println(systemPrompt);

        return chatClient
                .prompt()
                .system(systemPrompt)
                .user("Analyze the sentiment.")
                .call()
                .content();
    }
}