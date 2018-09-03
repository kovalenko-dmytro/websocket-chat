package com.dkovaleko.websocketchat.service.chat;

import com.dkovaleko.websocketchat.dto.chat.ChatMessage;

import java.util.List;

public interface ChatService {


    void save(ChatMessage chatMessage);

    List<ChatMessage> find(long roomID);
}
