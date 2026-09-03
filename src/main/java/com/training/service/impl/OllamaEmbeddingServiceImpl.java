package com.training.service.impl;

import com.training.service.EmbeddingService;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OllamaEmbeddingServiceImpl implements EmbeddingService {

    private final EmbeddingModel embeddingModel;
    public OllamaEmbeddingServiceImpl(@Qualifier("ollamaEmbeddingModel")EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
    }

    @Override
    public float[] embed(String text) {
        return embeddingModel.embed(text);
    }
}