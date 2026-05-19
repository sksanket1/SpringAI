package com.spring.ai.firstproject;

import com.spring.ai.firstproject.entity.Tut;
import com.spring.ai.firstproject.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ChatController {

//    private ChatClient openAIChatClient;
//    private ChatClient ollamaChatClient;

//    public ChatController(OpenAiChatModel openAIChatModel, OllamaChatModel ollamaChatModel) {
//        this.openAIChatClient = ChatClient.builder(openAIChatModel).build();
//        this.ollamaChatClient = ChatClient.builder(ollamaChatModel).build();
//    }

    private ChatClient chatClient;
    private ChatService chatService;

//    public ChatController(ChatClient.Builder chatClientBuilder) {
//        this.chatClient = chatClientBuilder.build();
//    }

//    public ChatController(@Qualifier("openAIChatClient") ChatClient openAIChatClient,
//                          @Qualifier("ollamaChatClient") ChatClient ollamaChatClient) {
//        this.openAIChatClient = openAIChatClient;
//        this.ollamaChatClient = ollamaChatClient;
//    }


    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> chat(@RequestParam(value = "q") String q) {
        return ResponseEntity.ok(chatService.chat(q));
    }

    @GetMapping("/chattut")
    public ResponseEntity<Tut> chattut(@RequestParam(value = "q") String q) {
        return ResponseEntity.ok(chatService.chattut(q));
    }

    @GetMapping("/chattutlist")
    public List<Tut> chattutlist(@RequestParam(value = "q") String q) {
        return ResponseEntity.ok(chatService.chattutlist(q)).getBody();
    }

    @GetMapping("/chattemplate")
    public ResponseEntity<String> chattemplate(@RequestParam(value = "q") String q) {
        return ResponseEntity.ok(chatService.chattemplate(q));
    }

    @GetMapping("/chatTemplateRender")
    public ResponseEntity<String> chatTemplateRender() {
        return ResponseEntity.ok(chatService.chatTemplateRender());
    }

    @GetMapping("/sysPromptTemplate")
    public ResponseEntity<String> sysPromptTemplate() {
        return ResponseEntity.ok(chatService.sysPromptTemplate());
    }

    @GetMapping("/promptFile")
    public ResponseEntity<String> promptFile() {
        return ResponseEntity.ok(chatService.promptFile());
    }
}