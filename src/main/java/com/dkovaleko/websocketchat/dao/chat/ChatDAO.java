package com.dkovaleko.websocketchat.dao.chat;

import com.dkovaleko.websocketchat.dto.chat.ChatMessage;

public interface ChatDAO {

    void save(ChatMessage chatMessage);
}
