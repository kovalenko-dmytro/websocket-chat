package com.dkovalenko.websocketchat.dao.chat.impl;

import com.dkovalenko.websocketchat.dao.chat.ChatRoomDAO;
import com.dkovalenko.websocketchat.dao.chat.mapper.ChatRoomRowMapper;
import com.dkovalenko.websocketchat.dto.chat.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
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

        return jdbcTemplate.query("SELECT room_id, room_name, user_id FROM chat_rooms",
                new ChatRoomRowMapper());
    }

    @Override
    public void save(ChatRoom chatRoom, long userID) {

        jdbcTemplate.update("INSERT INTO chat_rooms (room_name, user_id) VALUES(?,?)", chatRoom.getRoomName(), userID
                );
        jdbcTemplate.update("INSERT INTO chat_room_user (room_id, user_id) " +
                        "VALUES((SELECT room_id FROM chat_rooms WHERE room_name = ?), ?)", chatRoom.getRoomName(), userID
                );
    }

    @Override
    public List<ChatRoom> find(long userID) {

        Object[] params = {userID};

        return jdbcTemplate.query("SELECT cr.room_id, cr.room_name, cr.user_id FROM chat_rooms cr " +
                        "LEFT JOIN chat_room_user cru ON cr.room_id = cru.room_id " +
                        "WHERE cr.room_id = 1 OR cru.user_id = ? ",
                params,
                new ChatRoomRowMapper());
    }

    @Override
    public void saveInviteUser(long roomID, long userID) {

          Object[] params = {roomID, userID};

        jdbcTemplate.update("INSERT INTO chat_room_user (room_id, user_id) " +
                "VALUES(?, ?)",
                params);
    }

    @Override
    public void leaveRoom(long roomID, long userID) {

        Object[] params = {roomID, userID};

        jdbcTemplate.update("DELETE FROM chat_room_user WHERE room_id = ? AND user_id = ?",
                params);
    }

    @Override
    public int findAlreadyInvitedUser(long roomID, long userID) {

        Object[] params = {roomID, userID};

        return jdbcTemplate.queryForList("SELECT room_id, user_id FROM chat_room_user WHERE room_id = ? AND user_id = ? ", params).size();
    }

}
