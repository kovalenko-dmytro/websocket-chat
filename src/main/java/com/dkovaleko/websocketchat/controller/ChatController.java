package com.dkovaleko.websocketchat.controller;

import com.dkovaleko.websocketchat.dto.ChatMessage;
import com.dkovaleko.websocketchat.dto.MessageType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;


@Controller
public class ChatController {

    @MessageMapping("/chat/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {

        chatMessage.setCreatedDateTime(LocalDateTime.now());
        return chatMessage;
    }

    @MessageMapping("/chat/addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {

        headerAccessor.getSessionAttributes().put("userName", chatMessage.getSender());

        chatMessage.setCreatedDateTime(LocalDateTime.now());
        chatMessage.setMessageType(MessageType.JOIN);
        return chatMessage;

    }
}
