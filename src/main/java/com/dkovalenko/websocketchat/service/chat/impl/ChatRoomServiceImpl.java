package com.dkovalenko.websocketchat.service.chat.impl;

import com.dkovalenko.websocketchat.dao.chat.ChatRoomDAO;
import com.dkovalenko.websocketchat.dao.user.UserDAO;
import com.dkovalenko.websocketchat.dto.chat.ChatRoom;
import com.dkovalenko.websocketchat.dto.user.User;
import com.dkovalenko.websocketchat.service.chat.ChatRoomService;
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

    @Override
    public void leaveRoom(long roomID, long userID) {

        chatRoomDAO.leaveRoom(roomID, userID);
    }
}
