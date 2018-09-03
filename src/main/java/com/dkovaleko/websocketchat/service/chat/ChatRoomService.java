package com.dkovaleko.websocketchat.service.chat;

import com.dkovaleko.websocketchat.dto.chat.ChatRoom;

import java.util.List;

public interface ChatRoomService {

    List<ChatRoom> find();
}
