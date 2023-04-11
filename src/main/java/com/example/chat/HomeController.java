package com.example.chat;

import com.example.chat.model.ContactInfo;
import com.example.chat.model.MessageInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    String userName;

    @FXML
    private VBox contactVbox;

    @FXML
    private VBox messageVbox;

    @FXML
    private TextField textInfo;

    @FXML
    private Label name;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<ContactInfo> l = getContactInfo();
        for (int i = 0; i < l.size(); i++) {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("contact.fxml"));

            try {
                HBox hBox = fxmlLoader.load();

                Contact contact = fxmlLoader.getController();
                contact.setInfo(l.get(i));

                contactVbox.getChildren().add(hBox);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    // database Query
    private List<ContactInfo> getContactInfo() {

        List<ContactInfo> info = new ArrayList<>();

        info.add(new ContactInfo("0187", "Reju", "xyz.png"));
        info.add(new ContactInfo("0187", "Refat", "xyz.png"));
        info.add(new ContactInfo("0187", "Haydar", "xyz.png"));
        info.add(new ContactInfo("0187", "asif", "xyz.png"));
        info.add(new ContactInfo("0187", "Refat", "xyz.png"));


        return info;
    }

    @FXML
    void selectedContact() {
        name.setText(userName);
        System.out.println(userName);

        // message loading
    }

    @FXML
    void sendMassage() {

        String text = textInfo.getText();
        textInfo.setText("");

        if (text.length() !=0){
            String sender = "Rejuyan Ahmde";
            String time = "03/04/2023";
            MessageInfo messageInfo = new MessageInfo(sender,time,text);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("message.fxml"));

            try {
                HBox hBox = fxmlLoader.load();

                Message msg = fxmlLoader.getController();
                msg.generateMessage(messageInfo);
                messageVbox.getChildren().add(hBox);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void onEnter(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER){
            sendMassage();
        }

    }


}