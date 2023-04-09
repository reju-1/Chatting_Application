package com.example.chat.model;

public class MessageInfo {
    public String sender;
    public String time;
    public String messageText;

    public MessageInfo(String sender, String time, String messageText) {
        this.sender = sender;
        this.time = time;
        this.messageText = messageText;
    }

}
