package com.example.chat;

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

public class RegisterController {

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;

    @FXML
    private TextField userPhone;

    @FXML
    void getRegister(ActionEvent event) {

        try {
            DBUtil util = new DBUtil();

            String name = userName.getText();
            String pass = userPassword.getText();
            String number = userPhone.getText();

            if (pass.length() >= 4 && number.length() == 11 && number.matches("\\d+")) {

                if (util.register(number, name, pass)) {
                    FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("login.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(fxmlLoader1.load());
                    //    stage.getIcons().add(new Image(getClass().getResourceAsStream("online-course.png")));
                    stage.setTitle("Login");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                } else {
                    PopUpWindow.display("Error","User already exist!");
                }
            } else {
                String warningMessage = "";
                if (!number.matches("\\d+")) {
                    warningMessage ="The number is in valid and length must be 11";
                } else if (pass.length() < 4 && number.length() == 11 && name.length()<1) {
                    warningMessage = "Number must be 11 length and password must be at lest 4 length";
                } else if (pass.length() < 4) {
                    warningMessage = "password must be four length. ";
                } else if (number.length() < 11) {
                    warningMessage = "number must be length 11.";
                }else if (name.length()<1){
                    warningMessage ="invalid name";
                }
                PopUpWindow.display("Error",warningMessage);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goToLoginWindow(MouseEvent event) {

        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("login.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader1.load());
            //    stage.getIcons().add(new Image(getClass().getResourceAsStream("online-course.png")));
            stage.setTitle("SignUp");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
