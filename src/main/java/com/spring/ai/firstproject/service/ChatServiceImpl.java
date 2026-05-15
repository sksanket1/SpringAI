package com.spring.ai.firstproject.service;

import com.spring.ai.firstproject.entity.Tut;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatClient chatClient;

    public ChatServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public String chat(String query) {
        String prompt = "tell me about virat kohli";
        String content = chatClient.prompt().user(prompt)
                .system("As expert in cricekt").call().content();
        var metaData = chatClient.prompt(prompt).call().chatResponse().getResult().getOutput().getText();
        System.out.println("MetaData: " + metaData);
        return content;
    }

    @Override
    public Tut chattut(String query) {
        Prompt prompt = new Prompt(query);
        Tut tutorial = chatClient.prompt(prompt).call().entity(Tut.class);
        return tutorial;
    }

    @Override
    public List<Tut> chattutlist(String q) {
        Prompt prompt = new Prompt(query);
        Tut tutorial = chatClient.prompt(prompt).call().entity(Tut.class);
        return tutorial;
    }
}