package com.training.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

//    @Bean
//    public ChatClient chatClient(ChatClient.Builder builder) {
//        return builder.build();
//    }

    @Bean("geminiChatClient")
    public ChatClient geminiChatClient(
            @Qualifier("googleGenAiChatModel") ChatModel chatModel) {
        return ChatClient.builder(chatModel).defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    @Bean("openAiChatClient")
    public ChatClient openAiChatClient(
            @Qualifier("openAiChatModel") ChatModel chatModel) {
        return ChatClient.builder(chatModel).defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

    @Bean("ollamaChatClient")
    public ChatClient ollamaChatClient(
            @Qualifier("ollamaChatModel") ChatModel chatModel) {
        return ChatClient.builder(chatModel).defaultAdvisors(new SimpleLoggerAdvisor()).build();
    }

}