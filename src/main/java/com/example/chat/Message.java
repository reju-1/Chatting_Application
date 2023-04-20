package com.example.chat;

import com.example.chat.model.MessageInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class Message {

    @FXML
    private Text messageText;

    @FXML
    private Label receiver;

    @FXML
    private Label time;

    public void generateMessage(MessageInfo info) {
        receiver.setText(info.receiverName);
        time.setText(info.time);
        messageText.setText(info.messageText);
    }
}
