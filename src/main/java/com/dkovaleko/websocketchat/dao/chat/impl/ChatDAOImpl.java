package com.dkovaleko.websocketchat.dao.chat.impl;

import com.dkovaleko.websocketchat.dao.chat.ChatDAO;
import com.dkovaleko.websocketchat.dto.chat.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
