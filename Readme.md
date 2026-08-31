# Notes
```
                TEMPLATE FILE
                       │
             ┌─────────┴─────────┐
             │                   │
     PromptTemplate      SystemPromptTemplate
             │                   │
             ▼                   ▼
          Prompt          System Message
             │                   │
             └─────────┬─────────┘
                       ▼
                  ChatClient
                       │
                       ▼
                    AI Model
```

```
Spring AI
│
├── ChatModel
│      └── Text / conversational responses
│
├── EmbeddingModel
│      └── Text → vector embeddings
│
├── ImageModel
│      └── Text → image
│
└── AudioModel
└── Audio processing
```
# Git -> VCS

Maven Home in Ubuntu : /usr/share/maven
JDK in ubuntu : /usr/lib/jvm/java-25-openjdk-amd64