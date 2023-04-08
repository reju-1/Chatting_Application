module com.example.chat {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.chat to javafx.fxml;
    exports com.example.chat;
    opens com.example.chat.model to javafx.fxml;
    exports com.example.chat.model;
}