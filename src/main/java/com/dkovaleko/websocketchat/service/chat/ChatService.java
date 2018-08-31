package com.dkovaleko.websocketchat.service.chat;

import com.dkovaleko.websocketchat.dto.chat.ChatMessage;

public interface ChatService {


    void save(ChatMessage chatMessage);
}
