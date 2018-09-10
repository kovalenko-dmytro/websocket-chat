package com.dkovalenko.websocketchat.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageType {

    CHAT,
    JOIN,
    LEAVE
}
