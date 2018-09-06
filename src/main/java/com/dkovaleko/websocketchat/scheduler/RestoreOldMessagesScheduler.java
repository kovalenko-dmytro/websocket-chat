package com.dkovaleko.websocketchat.scheduler;

import com.dkovaleko.websocketchat.constants.ConstantsEnum;
import com.dkovaleko.websocketchat.service.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RestoreOldMessagesScheduler {

    private final ChatService chatService;

    @Autowired
    public RestoreOldMessagesScheduler(ChatService chatService) {
        this.chatService = chatService;
    }

    //every month at 04:00 a.m
    @Scheduled(cron = "0 0 4 1 * ?")
    public void restoreOldMessages() {

        chatService.delete(ConstantsEnum.DELETE_OLD_MESSAGES_INTERVAL_IN_HOURS.getValue());
    }
}
