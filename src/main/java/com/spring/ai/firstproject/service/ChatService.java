package com.spring.ai.firstproject.service;

import ch.qos.logback.core.model.util.TagUtil;
import com.spring.ai.firstproject.entity.Tut;

import java.util.List;

public interface ChatService {
    String chat(String query);

    Tut chattut(String query);

    List<Tut> chattutlist(String q);

    String chattemplate(String q);

    String chatTemplateRender();

    String sysPromptTemplate();

    String promptFile();
}
