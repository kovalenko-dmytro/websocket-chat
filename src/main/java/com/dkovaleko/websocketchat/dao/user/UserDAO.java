package com.dkovaleko.websocketchat.dao.user;

import com.dkovaleko.websocketchat.dto.user.User;

import java.util.List;

public interface UserDAO {

    List<User> find();
    User find(long userID);
}
