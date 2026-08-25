package com.training.service;

import reactor.core.publisher.Flux;

public interface ChatService {
     String chat(String message);

    default Flux<String> stream(String message){
        return Flux.just(message);
    }
}
