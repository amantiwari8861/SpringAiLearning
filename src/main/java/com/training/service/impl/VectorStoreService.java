package com.training.service.impl;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VectorStoreService {
    private final VectorStore vectorStore;
    public VectorStoreService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }
    public void addDocuments() {

        List<Document> documents = List.of(

                new Document(
                        "Spring Boot is a Java framework for building applications."
                ),

                new Document(
                        "Spring Security provides authentication and authorization."
                ),

                new Document(
                        "Spring AI helps developers build AI applications using Spring."
                )
        );

        vectorStore.add(documents);
    }
    public List<Document> search(String query) {

        return vectorStore.similaritySearch(query);
    }
}