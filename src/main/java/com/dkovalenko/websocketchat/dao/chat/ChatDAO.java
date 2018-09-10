package com.dkovalenko.websocketchat.dao.chat;

import com.dkovalenko.websocketchat.dto.chat.ChatMessage;

import java.util.List;

public interface ChatDAO {

    void save(ChatMessage chatMessage);

    List<ChatMessage> find(long roomID, long interval);

    void delete(long restoreOldMessagesInterval);
}
