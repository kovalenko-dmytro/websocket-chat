package com.dkovaleko.websocketchat.controller.chat;

import com.dkovaleko.websocketchat.dto.chat.ChatRoom;
import com.dkovaleko.websocketchat.service.chat.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping(value = "/users/{userID}/chat/rooms/create")
    public ModelAndView createRoom(@PathVariable(value = "userID") long userID,
                                   @ModelAttribute("chatRoom") ChatRoom chatRoom) {

        ModelAndView view = new ModelAndView();

        chatRoomService.save(chatRoom);

        view.setViewName("redirect:/users/" + userID + "/chat" + "");

        return view;
    }

    @GetMapping(value = "/users/{userID}/chat/rooms/change")
    public ModelAndView changeRoom(@PathVariable(value = "userID") long userID) {

        ModelAndView view = new ModelAndView();

        view.setViewName("redirect:/users/" + userID + "/chat" + "");

        return view;
    }
}
