package com.example.chat;

import com.example.chat.model.DBUtil;
import com.example.chat.model.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField phoneNumber;

    @FXML
    void getLogin(ActionEvent event) {

        String number = phoneNumber.getText();
        String pass = password.getText();

        DBUtil util = new DBUtil();

        if (pass.length() >= 4 && number.length() == 11 && number.matches("\\d+") || true) {
            String userName = util.logIn(number, pass);
            if (userName.length() != 0 || true) {
                UserData.name = userName;
                UserData.id = number;

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene;
                try {
                    scene = new Scene(fxmlLoader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                stage.setTitle("Hi " + userName + " !");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } else {
                PopUpWindow.display("Error", "Login info is wrong");
            }
        } else {
            String warningMessage = "";
            if (number.length() == 0 && pass.length() == 0) {
                warningMessage = "Fields can't be empty";
            } else if (!number.matches("\\d+")) {
                warningMessage = "The number is in valid and length must be 11";
            } else if (pass.length() < 4 && number.length() < 11) {
                warningMessage = "Number must be 11 length and password must be at lest 4 length";
            } else if (pass.length() < 4) {
                warningMessage = "password must be four length. ";
            } else if (number.length() != 11) {
                warningMessage = "number must be length 11.";
            }
            PopUpWindow.display("Error", warningMessage);
        }

    }

    @FXML
    void goToRegisterWidow(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("register.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader1.load());
            //    stage.getIcons().add(new Image(getClass().getResourceAsStream("online-course.png")));
            stage.setTitle("Register");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
