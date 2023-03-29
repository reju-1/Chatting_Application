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

public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField phoneNumber;

    @FXML
    void getLogin(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("home.fxml"));
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

    @FXML
    void goToRegisterWidow(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("register.fxml"));
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
