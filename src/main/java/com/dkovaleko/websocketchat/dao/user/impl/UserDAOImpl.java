package com.dkovaleko.websocketchat.dao.user.impl;

import com.dkovaleko.websocketchat.dao.user.UserDAO;
import com.dkovaleko.websocketchat.dao.user.mapper.UserRowMapper;
import com.dkovaleko.websocketchat.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> find() {

        return jdbcTemplate.query("SELECT user_id, name FROM users",
                new UserRowMapper());
    }

    @Override
    public User find(long userID) {

        Object[] params = {userID};

        return jdbcTemplate.queryForObject("SELECT user_id, name FROM users WHERE user_id = ?",
                params,
                new UserRowMapper());
    }
}
