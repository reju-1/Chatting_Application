package com.example.chat;

import com.example.chat.model.ContactInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private VBox contactVbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<ContactInfo> l = new ArrayList<>(getContactInfo());

        for (int i= 0; i<l.size(); i++){

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("contact.fxml"));

            System.out.printf(l.get(i).getName());
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

        System.out.println("hi");;

        return info;
    }

}