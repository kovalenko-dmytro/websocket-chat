package com.dkovaleko.websocketchat.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    private long messageID;
    private MessageType messageType;
    private String content;
    private String sender;
    private String createdDateTime;
    private long userID;

    public void setCreatedDateTime(LocalDateTime dateTime) {
        this.createdDateTime = dateTime.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy HH:mm:ss a"));
    }
}
