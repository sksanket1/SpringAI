package com.spring.ai.firstproject.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean(name = "openAIChatClient")
    public ChatClient openAIChatClient(OpenAiChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }

    @Bean(name = "ollamaChatClient")
    public ChatClient ollamaChatModel(OllamaChatModel chatModel) {
        return ChatClient.builder(chatModel).build();
    }
}