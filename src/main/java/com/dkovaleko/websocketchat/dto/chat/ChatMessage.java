package com.dkovaleko.websocketchat.dto.chat;

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
    private String sender;
    private LocalDateTime createdDateTime;
    private long senderID;
    private ChatRoom chatRoom;
    private List<ChatMessage> messages;

    public ChatMessage(long messageID, MessageType messageType, String content,
                       String sender, LocalDateTime createdDateTime, long senderID, ChatRoom chatRoom) {

        this.messageID = messageID;
        this.messageType = messageType;
        this.content = content;
        this.sender = sender;
        this.createdDateTime = createdDateTime;
        this.senderID = senderID;
        this.chatRoom = chatRoom;
    }
}
