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
                chatMessage.getSenderID(),
                chatMessage.getReceiverID(),
                chatMessage.getChatRoom().getRoomID(),
                chatMessage.getCreatedDateTime()
        };

        jdbcTemplate.update("INSERT INTO messages (message_type, message_content, sender_id, receiver_id, room_id, created) " +
                        "VALUES(?,?,?,?,?,?)",
                params);

    }

    @Override
    public List<ChatMessage> find(long roomID, long interval) {

        Object[] params = {roomID, interval};

        return jdbcTemplate.query("SELECT m.message_id, m.message_type, m.message_content, " +
                        "m.sender_id, m.receiver_id, m.created, sender.name sender, receiver.name receiver, " +
                        "cr.room_id, cr.room_name " +
                        "FROM messages m " +
                        "INNER JOIN users sender ON m.sender_id = sender.user_id " +
                        "INNER JOIN users receiver ON m.receiver_id = receiver.user_id " +
                        "INNER JOIN chat_rooms cr ON m.room_id = cr.room_id " +
                        "WHERE m.room_id = ? AND m.created >= now() - INTERVAL ? hour " +
                        "ORDER BY m.created",
                params,
                new ChatMessageRowMapper());
    }

    @Override
    public void delete(long restoreOldMessagesInterval) {

        Object[] params = {restoreOldMessagesInterval};

        jdbcTemplate.update("DELETE FROM messages WHERE created < now() - INTERVAL ? hour ", params);
    }
}
