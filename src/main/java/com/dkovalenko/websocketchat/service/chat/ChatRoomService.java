package com.dkovalenko.websocketchat.service.chat;

import com.dkovalenko.websocketchat.dto.chat.ChatRoom;

import java.util.List;

public interface ChatRoomService {

    List<ChatRoom> find();

    void save(ChatRoom chatRoom, long userID);

    List<ChatRoom> find(long userID);

    void saveInviteUser(long roomID, long userID);
}
