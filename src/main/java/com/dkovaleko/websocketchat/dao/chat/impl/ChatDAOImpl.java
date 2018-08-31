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
                chatMessage.getUserID()
        };

        jdbcTemplate.update("INSERT INTO messages (message_type, message_content, created, user_id) " +
                        "VALUES(?,?,?,?)",
                params);

    }

    @Override
    public List<ChatMessage> find(long userID, long interval) {
        return null;
    }

    @Override
    public List<ChatMessage> find(long interval) {

        Object[] params = {interval};

        return jdbcTemplate.query("SELECT m.message_id, m.message_type, m.message_content, m.created, m.user_id, u.name " +
                        "FROM messages m " +
                        "INNER JOIN users u ON m.user_id = u.user_id " +
                        "WHERE m.created >= now() - INTERVAL ? hour " +
                        "ORDER BY m.created",
                params,
                new ChatMessageRowMapper());
    }
}
