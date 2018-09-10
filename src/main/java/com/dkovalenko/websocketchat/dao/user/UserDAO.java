package com.dkovalenko.websocketchat.dao.user;

import com.dkovalenko.websocketchat.dto.user.User;

import java.util.List;

public interface UserDAO {

    List<User> find();
    User find(long userID);
}
