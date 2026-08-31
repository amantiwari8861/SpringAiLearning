package com.training.service;

import jakarta.validation.constraints.NotBlank;
import org.jspecify.annotations.Nullable;
import reactor.core.publisher.Flux;

public interface ChatService {
     String chat(String message);
    default String chat(String conversationId,String message){return null;}
    default Flux<String> stream(String message){
        return Flux.just(message);
    }

    default String generateImage(String message){throw new UnsupportedOperationException("Not supported yet.");}
}
