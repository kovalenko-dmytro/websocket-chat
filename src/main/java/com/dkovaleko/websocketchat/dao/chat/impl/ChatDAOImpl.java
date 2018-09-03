package com.dkovaleko.websocketchat.dao.chat.impl;

import com.dkovaleko.websocketchat.dao.chat.ChatDAO;
import com.dkovaleko.websocketchat.dao.chat.mapper.ChatMessageRowMapper;
import com.dkovaleko.websocketchat.dto.chat.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatDAOImpl implements ChatDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ChatDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(ChatMessage chatMessage) {

        Object[] params = {
                chatMessage.getMessageType().toString(),
                chatMessage.getContent(),
                chatMessage.getCreatedDateTime(),
                chatMessage.getSenderID(),
                chatMessage.getChatRoom().getRoomID()
        };

        jdbcTemplate.update("INSERT INTO messages (message_type, message_content, created, sender_id, room_id) " +
                        "VALUES(?,?,?,?,?)",
                params);

    }

    @Override
    public List<ChatMessage> find(long roomID, long interval) {

        Object[] params = {roomID, interval};

        return jdbcTemplate.query("SELECT m.message_id, m.message_type, m.message_content, m.created, m.sender_id, u.name, cr.room_id, cr.room_name " +
                        "FROM messages m " +
                        "INNER JOIN users u ON m.sender_id = u.user_id " +
                        "INNER JOIN chat_rooms cr ON m.room_id = cr.room_id " +
                        "WHERE m.room_id = ? AND m.created >= now() - INTERVAL ? hour " +
                        "ORDER BY m.created",
                params,
                new ChatMessageRowMapper());
    }
}
