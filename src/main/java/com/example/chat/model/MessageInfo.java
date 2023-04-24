package com.example.chat.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageInfo {
    public String senderId;
    public String senderName;
    public String receiverId;
    public String receiverName;
    public String time;
    public String messageText;

    public MessageInfo(String senderId,String senderName, String receiverId,String receiverName, String messageText) {
        this.senderId = senderId;
        this.senderName = senderName;

        this.receiverId = receiverId;
        this.receiverName = receiverName;
        this.messageText = messageText;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        this.time = dateFormat.format(date);
    }

    public MessageInfo(String senderId,String senderName, String receiverId,String receiverName,String time ,String messageText) {
        this.senderId = senderId;
        this.senderName = senderName;

        this.receiverId = receiverId;
        this.receiverName = receiverName;
        this.messageText = messageText;

        this.time = time;
    }

}
