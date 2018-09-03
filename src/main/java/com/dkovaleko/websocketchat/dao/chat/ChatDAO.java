package com.dkovaleko.websocketchat.dao.chat;

import com.dkovaleko.websocketchat.dto.chat.ChatMessage;

import java.util.List;

public interface ChatDAO {

    void save(ChatMessage chatMessage);

    List<ChatMessage> find(long roomID, long interval);
}
