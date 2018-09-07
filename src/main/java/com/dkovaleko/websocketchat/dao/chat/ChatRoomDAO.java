package com.dkovaleko.websocketchat.dao.chat;

import com.dkovaleko.websocketchat.dto.chat.ChatRoom;

import java.util.List;

public interface ChatRoomDAO {

    List<ChatRoom> find();

    void save(ChatRoom chatRoom, long userID);

    List<ChatRoom> find(long userID);

}
