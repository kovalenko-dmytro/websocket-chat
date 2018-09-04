package com.dkovaleko.websocketchat.service.chat.impl;

import com.dkovaleko.websocketchat.dao.chat.ChatRoomDAO;
import com.dkovaleko.websocketchat.dto.chat.ChatRoom;
import com.dkovaleko.websocketchat.service.chat.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomDAO chatRoomDAO;

    @Autowired
    public ChatRoomServiceImpl(ChatRoomDAO chatRoomDAO) {
        this.chatRoomDAO = chatRoomDAO;
    }

    @Override
    public List<ChatRoom> find() {
        return chatRoomDAO.find();
    }

    @Override
    public void save(ChatRoom chatRoom) {
        chatRoomDAO.save(chatRoom);
    }
}
