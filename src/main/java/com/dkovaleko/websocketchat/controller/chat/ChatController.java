package com.dkovaleko.websocketchat.controller.chat;

import com.dkovaleko.websocketchat.dto.chat.ChatMessage;
import com.dkovaleko.websocketchat.dto.chat.ChatRoom;
import com.dkovaleko.websocketchat.service.chat.ChatRoomService;
import com.dkovaleko.websocketchat.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @MessageMapping("/chat/sendMessage/{roomID}")
    @SendTo("/topic/public/{roomID}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage, @DestinationVariable String roomID) {

        chatMessage.setCreatedDateTime(LocalDateTime.now());
        chatService.save(chatMessage);

        return chatMessage;
    }

    @MessageMapping("/chat/addUser/{roomID}")
    @SendTo("/topic/public/{roomID}")
    public List<ChatMessage> addUser(@Payload ChatMessage chatMessage, @DestinationVariable String roomID,
                               SimpMessageHeaderAccessor headerAccessor) {

        headerAccessor.getSessionAttributes().put("userName", chatMessage.getSender());

        chatMessage.setCreatedDateTime(LocalDateTime.now());

        List<ChatMessage> messages = chatService.find(chatMessage.getChatRoom().getRoomID());
        messages.add(chatMessage);

        return messages;
    }
}
