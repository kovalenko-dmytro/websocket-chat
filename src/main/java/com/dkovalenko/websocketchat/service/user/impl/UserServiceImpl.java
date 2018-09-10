package com.dkovalenko.websocketchat.service.user.impl;

import com.dkovalenko.websocketchat.dao.user.UserDAO;
import com.dkovalenko.websocketchat.dto.user.User;
import com.dkovalenko.websocketchat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> find() {
        return userDAO.find();
    }

    @Override
    public User find(long userID) {
        return userDAO.find(userID);
    }
}
