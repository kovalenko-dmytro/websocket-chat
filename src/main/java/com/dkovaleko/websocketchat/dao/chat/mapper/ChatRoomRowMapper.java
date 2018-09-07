package com.dkovaleko.websocketchat.dao.chat.mapper;

import com.dkovaleko.websocketchat.dto.chat.ChatRoom;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatRoomRowMapper implements RowMapper<ChatRoom> {

    @Override
    public ChatRoom mapRow(ResultSet resultSet, int i) throws SQLException {

        return new ChatRoom(
                resultSet.getLong("room_id"),
                resultSet.getString("room_name"),
                resultSet.getLong("user_id")
        );
    }
}
