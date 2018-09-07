package com.dkovaleko.websocketchat.service.chat;

import com.dkovaleko.websocketchat.dto.chat.ChatRoom;
import com.dkovaleko.websocketchat.dto.user.User;

import java.util.List;

public interface ChatRoomService {

    List<ChatRoom> find();

    void save(ChatRoom chatRoom, long userID);

    List<ChatRoom> find(long userID);

    void saveInviteUser(long roomID, long userID);
}
