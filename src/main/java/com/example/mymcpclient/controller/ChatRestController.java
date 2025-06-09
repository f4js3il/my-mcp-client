package com.example.mymcpclient.controller;

import com.example.mymcpclient.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatRestController {

    public final ChatService chatService;

    public ChatRestController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/weatherinfo")
    public ResponseEntity<String> sendMessage(@RequestParam String city, @RequestParam String state ){
        return ResponseEntity.ok(chatService.getChatResponse(city,state));
    }
}
