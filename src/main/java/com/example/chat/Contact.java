package com.example.chat;

import com.example.chat.model.ContactInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Contact {

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    public void setInfo(ContactInfo info){

        //    Image img = new Image(getClass().getResourceAsStream(info.imgSrc));
        //    image.setImage(img);
        name.setText(info.getName());

    }

    @FXML
    void selectContract(MouseEvent event) {

        System.out.println(name.getText());

        // pass the name
    }

}
