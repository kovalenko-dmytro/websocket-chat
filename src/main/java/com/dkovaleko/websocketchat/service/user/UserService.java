package com.dkovaleko.websocketchat.service.user;

import com.dkovaleko.websocketchat.dto.user.User;

import java.util.List;

public interface UserService {

    List<User> find();

    User find(long userID);
}
