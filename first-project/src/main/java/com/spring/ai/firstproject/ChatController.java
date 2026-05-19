package com.spring.ai.firstproject;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ChatController {

    private ChatClient openAIChatClient;
    private ChatClient ollamaChatClient;

//    public ChatController(OpenAiChatModel openAIChatModel, OllamaChatModel ollamaChatModel) {
//        this.openAIChatClient = ChatClient.builder(openAIChatModel).build();
//        this.ollamaChatClient = ChatClient.builder(ollamaChatModel).build();
//    }

    public ChatController(@Qualifier("openAIChatClient") ChatClient openAIChatClient,
                          @Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {
        this.openAIChatClient = openAIChatClient;
        this.ollamaChatClient = ollamaChatClient;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q") String query) {
        String responseContent = this.ollamaChatClient.prompt(query).call().content();
        return ResponseEntity.ok(responseContent);
    }
}
