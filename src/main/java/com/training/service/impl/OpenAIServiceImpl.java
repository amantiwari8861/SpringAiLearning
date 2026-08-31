package com.training.service.impl;

import com.training.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
public class OpenAIServiceImpl implements ChatService {
    private final ChatClient chatClient;
    private final ImageModel imageModel;
    public OpenAIServiceImpl(@Qualifier("openAiChatClient") ChatClient chatClient,ImageModel imageModel) {
        this.chatClient = chatClient;
        this.imageModel = imageModel;
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

    @Override
    public String generateImage(String message) {

        ImagePrompt imagePrompt = new ImagePrompt(message);

        ImageResponse response = imageModel.call(imagePrompt);

        String image = response
                .getResult()
                .getOutput()
                .getB64Json();

        byte[] imageBytes = Base64.getDecoder().decode(image);

        Path imageDirectory = Path.of("images");

        try {
            Files.createDirectories(imageDirectory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String fileName = UUID.randomUUID()
                + "_"
                + LocalDateTime.now()
                .toString()
                .replace(":", "_")
                + ".png";

        Path path = imageDirectory.resolve(fileName);

        try (BufferedOutputStream bos = new BufferedOutputStream(
                Files.newOutputStream(path))) {

            bos.write(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "data:image/png;base64," + image;
    }

    public String chat(String conversationId,String message){
        return chatClient
                .prompt()
                .user(message)
                .advisors(advisor -> advisor
                        .param(
                                ChatMemory.CONVERSATION_ID,
                                conversationId
                        )
                )
                .call()
                .content();
    }

}
