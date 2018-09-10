package com.dkovalenko.websocketchat.dao.chat.mapper;

import com.dkovalenko.websocketchat.dto.chat.ChatMessage;
import com.dkovalenko.websocketchat.dto.chat.ChatRoom;
import com.dkovalenko.websocketchat.dto.chat.MessageType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChatMessageRowMapper implements RowMapper<ChatMessage> {

    @Override
    public ChatMessage mapRow(ResultSet resultSet, int i) throws SQLException {

        return new ChatMessage(
                resultSet.getLong("message_id"),
                MessageType.valueOf(resultSet.getString("message_type")),
                resultSet.getString("message_content"),
                resultSet.getString("sender"),
                resultSet.getString("receiver"),
                resultSet.getLong("sender_id"),
                resultSet.getLong("sender_id"),
                new ChatRoom(
                        resultSet.getLong("room_id"),
                        resultSet.getString("room_name"),
                        resultSet.getLong("owner")
                ),
                resultSet.getTimestamp("created").toLocalDateTime()
        );
    }
}
