package com.example.chat.model;

public class MessageInfo {
    public String senderId;
    public String receiverId;
    public String receiverName;
    public String time;
    public String messageText;

    public MessageInfo(String senderId, String receiverId,String receiverName, String time, String messageText) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.receiverName = receiverName;
        this.time = time;
        this.messageText = messageText;
    }

}
