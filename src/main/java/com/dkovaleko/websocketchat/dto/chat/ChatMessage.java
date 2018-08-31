package com.dkovaleko.websocketchat.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


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
    private List<ChatMessage> messages;

    public void setCreatedDateTime(LocalDateTime dateTime) {
        this.createdDateTime = dateTime.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy HH:mm:ss a"));
    }

    public ChatMessage(long messageID, MessageType messageType, String content, String sender, String createdDateTime, long userID) {
        this.messageID = messageID;
        this.messageType = messageType;
        this.content = content;
        this.sender = sender;
        this.createdDateTime = createdDateTime;
        this.userID = userID;
    }
}
