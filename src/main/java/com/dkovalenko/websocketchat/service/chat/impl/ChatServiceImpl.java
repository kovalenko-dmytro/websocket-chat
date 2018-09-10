package com.dkovalenko.websocketchat.service.chat.impl;

import com.dkovalenko.websocketchat.constants.ConstantsEnum;
import com.dkovalenko.websocketchat.dao.chat.ChatDAO;
import com.dkovalenko.websocketchat.dto.chat.ChatMessage;
import com.dkovalenko.websocketchat.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatDAO chatDAO;

    @Autowired
    public ChatServiceImpl(ChatDAO chatDAO) {
        this.chatDAO = chatDAO;
    }

    @Override
    public void save(ChatMessage chatMessage) {

        chatMessage.setCreatedDateTime(LocalDateTime.now());
        chatDAO.save(chatMessage);
    }

    @Override
    public List<ChatMessage> find(long roomID) {

        return chatDAO.find(roomID, ConstantsEnum.LAST_MESSAGES_INTERVAL_IN_HOURS.getValue());
    }

    @Override
    public void delete(long restoreOldMessagesInterval) {

        chatDAO.delete(restoreOldMessagesInterval);
    }
}
