package com.dkovaleko.websocketchat.controller.user;

import com.dkovaleko.websocketchat.dto.user.User;
import com.dkovaleko.websocketchat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public ModelAndView find() {

        ModelAndView view = new ModelAndView();

        List<User> users = userService.find();

        view.addObject("users", users);
        view.setViewName("index");

        return view;
    }

    @GetMapping(value = "/users/{userID}")
    public ModelAndView find(@PathVariable(value = "userID") long userID) {

        ModelAndView view = new ModelAndView();

        User user = userService.find(userID);

        view.addObject("user", user);
        view.setViewName("chat");

        return view;
    }
}
