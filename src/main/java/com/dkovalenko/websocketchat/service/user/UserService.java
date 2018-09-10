package com.dkovalenko.websocketchat.service.user;

import com.dkovalenko.websocketchat.dto.user.User;

import java.util.List;

public interface UserService {

    List<User> find();

    User find(long userID);
}
