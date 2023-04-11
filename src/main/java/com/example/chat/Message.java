package com.example.chat;

import com.example.chat.model.MessageInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class Message {

    @FXML
    private Text messageText;

    @FXML
    private Label sender;

    @FXML
    private Label time;

    public void generateMessage(MessageInfo info) {
        sender.setText(info.sender);
        time.setText(info.time);
        messageText.setText(info.messageText);
    }
}
