package com.example.mymcpclient.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatService {

    private final ChatClient chatClient;

    @Value("classpath:/templates/prompt.st")
    private Resource template;

    public ChatService(ChatClient.Builder chatClientBuilder, ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = chatClientBuilder.defaultToolCallbacks(toolCallbackProvider).build();
    }

    public String getChatResponse(String city, String state){
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Message userMessage = promptTemplate.createMessage(Map.of("city",city,"state",state));
         ChatResponse response=chatClient.prompt(new Prompt(userMessage)).call().chatResponse();
        return response.getResult().getOutput().getText();

    }
}
