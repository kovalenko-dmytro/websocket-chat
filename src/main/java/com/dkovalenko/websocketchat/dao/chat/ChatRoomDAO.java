package com.dkovalenko.websocketchat.dao.chat;

import com.dkovalenko.websocketchat.dto.chat.ChatRoom;

import java.util.List;

public interface ChatRoomDAO {

    List<ChatRoom> find();

    void save(ChatRoom chatRoom, long userID);

    List<ChatRoom> find(long userID);

    void saveInviteUser(long roomID, long userID);

    void leaveRoom(long roomID, long userID);
}
