package com.dkovalenko.websocketchat.dao.user.mapper;

import com.dkovalenko.websocketchat.dto.user.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        return new User(
                resultSet.getLong("user_id"),
                resultSet.getString("name")
        );
    }
}
