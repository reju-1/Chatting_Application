package com.example.chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddFriend {

    @FXML
    private TextField nameInputField;

    @FXML
    private TextField numberInputField;

    @FXML
    private Label status;

    @FXML
    void UpdateContact(ActionEvent event) {

        String name = nameInputField.getText();
        String number = numberInputField.getText();

        if (name.length() != 0 && number.length() == 11){


            status.setText("Submitted");
        }else {
            status.setText("Enter proper information");
        }
    }

}
