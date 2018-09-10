package com.dkovalenko.websocketchat.service.chat;

import com.dkovalenko.websocketchat.dto.chat.ChatMessage;

import java.util.List;

public interface ChatService {


    void save(ChatMessage chatMessage);

    List<ChatMessage> find(long roomID);

    void delete(long restoreOldMessagesInterval);
}
