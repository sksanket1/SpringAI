package com.spring.ai.firstproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.ai.firstproject.entity.Tut;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatServiceImpl implements ChatService {

    private ChatClient chatClient;

    private static final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Value("classpath:/prompts/user-message.st")
    private Resource userMessage;

    public ChatServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @Override
    public String chat(String query) {
        String prompt = "tell me about virat kohli";
        String content = chatClient.prompt().user(prompt).system("As expert in cricekt").call().content();
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
        Prompt prompt = new Prompt(q);
        List<Tut> tutlist = chatClient.prompt(prompt).call().entity(new ParameterizedTypeReference<List<Tut>>() {
        });
        return tutlist;
    }

    @Override
    public String chattemplate(String q) {
        Prompt prompt = new Prompt(q);
        String queryString = "As an expert in coding. Always write code in java. Now reply this question:{q}";
        var tut = chatClient.prompt().user(u -> u.text(queryString).param("q", q)).call().content();
        return tut;
    }

    @Override
    public String chatTemplateRender() {
        PromptTemplate strTemplate = PromptTemplate.builder().template("What is {techName}? tell me example {exampleName} ").build();
        String renderedString = strTemplate.render(Map.of("techName", "java", "exampleName", "spring exception"));
        Prompt prompt = new Prompt(renderedString);
        return this.chatClient.prompt(prompt).call().content();
    }

    @Override
    public String sysPromptTemplate() {
        var sysPromptTemplate = PromptTemplate.builder().template("You are a helpful coding assistant. You are expert in coding").build();
        var systemMessage = sysPromptTemplate.createMessage();
        var userTemplate = PromptTemplate.builder().template("What is {techName}? tell me example {exampleName} ").build();
        var userMessage = userTemplate.createMessage(Map.of("techName", "java", "exampleName", "spring exception"));
        Prompt prompt = new Prompt(systemMessage, userMessage);
        return this.chatClient.prompt(prompt).call().content();
    }

    @Override
    public String promptFile() {
        log.debug("Loading prompt file: {}", userMessage.getFilename());
        String result = this.chatClient.prompt()
                .system(sys -> sys.text("You are a helpful coding assistant"))
                .user(user -> user.text(this.userMessage)
                        .param("concept", "Spring Framework Validation"))
                .call().content();
        log.debug("PromptFile result: {}", result);
        return result;
    }
}