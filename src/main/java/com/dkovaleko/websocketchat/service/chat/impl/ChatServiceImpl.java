package com.dkovaleko.websocketchat.service.chat.impl;

import com.dkovaleko.websocketchat.dao.chat.ChatDAO;
import com.dkovaleko.websocketchat.dto.chat.ChatMessage;
import com.dkovaleko.websocketchat.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatDAO chatDAO;

    @Autowired
    public ChatServiceImpl(ChatDAO chatDAO) {
        this.chatDAO = chatDAO;
    }

    @Override
    public void save(ChatMessage chatMessage) {

        chatDAO.save(chatMessage);
    }
}
