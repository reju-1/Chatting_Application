package com.example.chat;

import com.example.chat.model.ContactInfo;
import com.example.chat.model.DBUtil;
import com.example.chat.model.MessageInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    String senderId;
    String receiverId;
    String receiverName;

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


//        DBUtil util = new DBUtil();
//        List<ContactInfo> l = util.getFriendList(senderId);
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
        name.setText(receiverName);
        System.out.println(receiverName);
        System.out.println(receiverId);

        // message loading
    }

    @FXML
    void sendMassage() {

        String text = textInfo.getText();
        textInfo.clear();

        if (text.length() != 0) {

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String time = dateFormat.format(date);
            MessageInfo messageInfo = new MessageInfo(senderId, receiverId, receiverName, time, text);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("message.fxml"));

            try {
                HBox hBox = fxmlLoader.load();

                Message msg = fxmlLoader.getController();
                msg.generateMessage(messageInfo);
                messageVbox.getChildren().add(hBox);

                // database and networking qe


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    void onEnter(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            sendMassage();
        }

    }

    @FXML
    void logOut(MouseEvent event) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Do you want to logout");
        //   alert.setContentText("Press ok");

        if (alert.showAndWait().get() == ButtonType.OK) {

            try {

                Scene scene = new Scene(fxmlLoader.load());
                stage.setTitle("Login");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}