package com.dkovaleko.websocketchat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    private MessageType messageType;
    private String content;
    private String sender;
    private LocalDateTime createdDateTime;
}
