package com.example.chat;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUpWindow {

    public static void display(String title, String message) {

        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(250);

        Label label = new Label(message);
        label.setFont(new Font(17));
        Button button = new Button("Close");
        button.setFont(new Font(17));
        button.setOnAction(event -> stage.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
