package com.dkovalenko.websocketchat.controller.chat;

import com.dkovalenko.websocketchat.dto.chat.ChatRoom;
import com.dkovalenko.websocketchat.service.chat.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

        chatRoomService.save(chatRoom, userID);

        view.setViewName("redirect:/users/" + userID + "/chat" + "");

        return view;
    }

    @PostMapping(value = "/users/{userID}/chat/rooms/invite")
    public ModelAndView invite(@PathVariable(value = "userID") long userID,
                                    @RequestParam String inviteUserID,
                               @RequestParam String inviteRoomID) {

        ModelAndView view = new ModelAndView();

        chatRoomService.saveInviteUser(Long.parseLong(inviteRoomID), Long.parseLong(inviteUserID));

        view.setViewName("redirect:/users/" + userID + "/chat" + "");

        return view;
    }

    @PostMapping(value = "/users/{userID}/chat/rooms/leave")
    public ModelAndView leave(@PathVariable(value = "userID") long userID,
                               @RequestParam String leaveUserID,
                               @RequestParam String leaveRoomID) {

        ModelAndView view = new ModelAndView();

        chatRoomService.leaveRoom(Long.parseLong(leaveRoomID), Long.parseLong(leaveUserID));

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
