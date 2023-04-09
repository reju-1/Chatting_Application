package com.example.chat;

import com.example.chat.model.MessageInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Message {

    @FXML
    private Label messageText;

    @FXML
    private Label sender;

    @FXML
    private Label time;

    public void generateMessage(MessageInfo info){
        sender.setText(info.sender);
        time.setText(info.time);
        messageText.setText(info.messageText);
    }
}
