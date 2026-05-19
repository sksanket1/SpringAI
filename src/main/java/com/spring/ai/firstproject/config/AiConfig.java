package com.spring.ai.firstproject.config;

import com.spring.ai.firstproject.advisors.TokenPrintAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

//    @Bean(name = "openAIChatClient")
//    public ChatClient openAIChatClient(OpenAiChatModel chatModel) {
//        return ChatClient.builder(chatModel).build();
//    }
//
//    @Bean(name = "ollamaChatClient")
//    public ChatClient ollamaChatModel(OllamaChatModel chatModel) {
//        return ChatClient.builder(chatModel).build();
//    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultAdvisors(new TokenPrintAdvisor(), new SimpleLoggerAdvisor())
                .defaultSystem("you are a helpful assistant")
                .defaultOptions(OpenAiChatOptions.builder()
                        .model("gpt-4o-mini")
                        .temperature(0.3)
                        .maxTokens(200)
                        .build())
                .build();
    }
}