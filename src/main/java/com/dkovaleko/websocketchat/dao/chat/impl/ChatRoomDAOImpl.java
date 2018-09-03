package com.dkovaleko.websocketchat.dao.chat.impl;

import com.dkovaleko.websocketchat.dao.chat.ChatRoomDAO;
import com.dkovaleko.websocketchat.dao.chat.mapper.ChatRoomRowMapper;
import com.dkovaleko.websocketchat.dto.chat.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRoomDAOImpl implements ChatRoomDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ChatRoomDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ChatRoom> find() {

        return jdbcTemplate.query("SELECT room_id, room_name FROM chat_rooms",
                new ChatRoomRowMapper());
    }
}
