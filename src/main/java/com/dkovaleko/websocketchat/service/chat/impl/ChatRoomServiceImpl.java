package com.dkovaleko.websocketchat.service.chat.impl;

import com.dkovaleko.websocketchat.dao.chat.ChatRoomDAO;
import com.dkovaleko.websocketchat.dao.user.UserDAO;
import com.dkovaleko.websocketchat.dto.chat.ChatRoom;
import com.dkovaleko.websocketchat.dto.user.User;
import com.dkovaleko.websocketchat.service.chat.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomDAO chatRoomDAO;
    private final UserDAO userDAO;

    @Autowired
    public ChatRoomServiceImpl(ChatRoomDAO chatRoomDAO, UserDAO userDAO) {
        this.chatRoomDAO = chatRoomDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<ChatRoom> find() {
        return chatRoomDAO.find();
    }

    @Override
    public void save(ChatRoom chatRoom, long userID) {
        chatRoomDAO.save(chatRoom, userID);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<ChatRoom> find(long userID) {

        List<ChatRoom> chatRooms = chatRoomDAO.find(userID);

        if (chatRooms.size() == 1 ) {

            User user = userDAO.find(userID);
            ChatRoom newChatRoom = new ChatRoom();
            newChatRoom.setRoomName(user.getName());

            chatRoomDAO.save(newChatRoom, userID);
            chatRooms.add(newChatRoom);
        }

        return chatRooms;
    }

    @Override
    public void saveInviteUser(long roomID, long userID) {

        chatRoomDAO.saveInviteUser(roomID, userID);
    }
}
