package com.dkovalenko.websocketchat.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {

    private long messageID;
    private MessageType messageType;
    private String content;
    private String senderName;
    private String receiverName;
    private long senderID;
    private long receiverID;
    private ChatRoom chatRoom;
    private LocalDateTime createdDateTime;
    private List<ChatMessage> messages;

    public ChatMessage(long messageID, MessageType messageType, String content,
                       String senderName, String receiverName, long senderID, long receiverID,
                       ChatRoom chatRoom, LocalDateTime createdDateTime) {

        this.messageID = messageID;
        this.messageType = messageType;
        this.content = content;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.chatRoom = chatRoom;
        this.createdDateTime = createdDateTime;
    }
}
