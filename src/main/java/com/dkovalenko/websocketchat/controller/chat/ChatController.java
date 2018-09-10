package com.dkovalenko.websocketchat.controller.chat;

import com.dkovalenko.websocketchat.dto.chat.ChatMessage;
import com.dkovalenko.websocketchat.service.chat.ChatRoomService;
import com.dkovalenko.websocketchat.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;


@Controller
public class ChatController {

    private final ChatService chatService;
    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatController(ChatService chatService, ChatRoomService chatRoomService) {
        this.chatService = chatService;
        this.chatRoomService = chatRoomService;
    }

    @MessageMapping("/chat/{userID}/sendMessage/{roomID}")
    @SendTo("/topic/public/{userID}/{roomID}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage,
                                   @DestinationVariable String userID,
                                   @DestinationVariable String roomID) {

        chatService.save(chatMessage);

        return chatMessage;
    }

    @MessageMapping("/chat/{userID}/addUser/{roomID}")
    @SendTo("/topic/public/{userID}/{roomID}")
    public List<ChatMessage> addUser(@Payload ChatMessage chatMessage,
                                     @DestinationVariable String userID,
                                     @DestinationVariable String roomID,
                                     SimpMessageHeaderAccessor headerAccessor) {

        headerAccessor.getSessionAttributes().put("userName", chatMessage.getSenderName());

        chatMessage.setCreatedDateTime(LocalDateTime.now());

        List<ChatMessage> messages = chatService.find(chatMessage.getChatRoom().getRoomID());
        messages.add(chatMessage);

        return messages;
    }
}