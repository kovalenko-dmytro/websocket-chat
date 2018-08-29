package com.dkovaleko.websocketchat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageType {

    CHAT("chat"),
    JOIN("join"),
    LEAVE("leave");

    private String messageType;
}
