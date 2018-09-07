package com.dkovaleko.websocketchat.controller.user;

import com.dkovaleko.websocketchat.dto.chat.ChatRoom;
import com.dkovaleko.websocketchat.dto.user.User;
import com.dkovaleko.websocketchat.service.chat.ChatRoomService;
import com.dkovaleko.websocketchat.service.chat.ChatService;
import com.dkovaleko.websocketchat.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserService userService;
    private final ChatService chatService;
    private final ChatRoomService chatRoomService;

    @Autowired
    public UserController(UserService userService, ChatService chatService, ChatRoomService chatRoomService) {
        this.userService = userService;
        this.chatService = chatService;
        this.chatRoomService = chatRoomService;
    }

    @GetMapping(value = "/")
    public ModelAndView find() {

        ModelAndView view = new ModelAndView();

        List<User> users = userService.find();

        view.addObject("users", users);
        view.setViewName("index");

        return view;
    }

    @GetMapping(value = "/users/{userID}/chat")
    public ModelAndView chat(@PathVariable(value = "userID") long userID, ChatRoom chatRoom) {

        ModelAndView view = new ModelAndView();

        User user = userService.find(userID);

        view.addObject("users", userService.find().stream().filter(u -> !u.getName().equals(user.getName())).collect(Collectors.toList()));
        view.addObject("user", user);
        view.addObject("rooms",chatRoomService.find(userID));
        view.addObject("chatRoom", chatRoom);
        view.setViewName("chat");

        return view;
    }
}
