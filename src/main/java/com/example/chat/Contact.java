package com.example.chat;

import com.example.chat.model.ContactInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Contact {

    @FXML
    private ImageView image;

    @FXML
    private Label id;

    @FXML
    private Label name;

    public void setInfo(ContactInfo info){

        //    Image img = new Image(getClass().getResourceAsStream(info.imgSrc));
        //    image.setImage(img);
        name.setText(info.getName());
        id.setText(info.getNumber());

    }

    @FXML
    void selectContract(MouseEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load());

            HomeController controller = loader.getController();
            controller.receiverName = name.getText();
            controller.receiverId = id.getText();
            controller.selectedContact();

            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
