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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;
import java.util.Date;

public class HomeController implements Initializable {

    BufferedReader reader;
    BufferedWriter writer;

    static String senderId = "01872088111";
    static String senderName;

    String receiverId;
    String receiverName;

    public static final Map<String, Scene> scene = new HashMap<>();

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


        DBUtil util = new DBUtil();
        List<ContactInfo> l = util.getFriendList(senderId);
//        connectToServer(); // creating socket
//        List<ContactInfo> l = getContactInfo();

        System.out.println("from initialize methode => sender id: " + senderId + " Name : " + senderName);

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

        info.add(new ContactInfo("111", "Reju", "xyz.png"));
        info.add(new ContactInfo("8465132", "Refat", "xyz.png"));
        info.add(new ContactInfo("222", "Haydar", "xyz.png"));
        info.add(new ContactInfo("0187", "asif", "xyz.png"));
        info.add(new ContactInfo("4165325", "Refat", "xyz.png"));


        return info;
    }

    @FXML
    void selectedContact() {

        name.setText(receiverName);
        System.out.println("from selectContact=> senderId:" + senderId + " Name:" + senderName + " ReceiverId:" + receiverId + " ReceiverName:" + receiverName);
        DBUtil util = new DBUtil();
        ArrayList<MessageInfo> messages = util.getMessages(senderId, senderName, receiverId, receiverName);

        for (MessageInfo message : messages) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("message.fxml"));

            try {
                HBox hBox = fxmlLoader.load();

                Message msg = fxmlLoader.getController();
                msg.generateMessage(message);
                messageVbox.getChildren().add(hBox);
                //sendThroughNetwork(message);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @FXML
    void sendMassage() {

        String text = textInfo.getText();
        textInfo.clear();

        if (text.length() != 0) {

            MessageInfo messageInfo = new MessageInfo( senderId, receiverName,receiverId, senderName, text);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("message.fxml"));

            try {
                HBox hBox = fxmlLoader.load();

                Message msg = fxmlLoader.getController();
                msg.generateMessage(messageInfo);
                messageVbox.getChildren().add(hBox);

                sendThroughNetwork(messageInfo);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


    @FXML
    void addFriend(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add-friend.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);

            Scene scene = new Scene(fxmlLoader.load());

            stage.setTitle("Add Friend");
            stage.setScene(scene);
            stage.setResizable(false);
//            stage.showAndWait();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void webView(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("web-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene.put("home", textInfo.getScene());

            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("WebView");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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

    void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 6000);

            OutputStreamWriter o = new OutputStreamWriter(socket.getOutputStream());
            writer = new BufferedWriter(o);

            InputStreamReader r = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(r);

            writer.write(senderId + "\n");
            writer.flush();

            Thread serverListener = new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String messageToken = reader.readLine() + "\n";
                            System.out.println(messageToken);
                            String[] parts = messageToken.split("##");

                            String senderId = parts[0];
                            String senderName = parts[1];
                            String receiverId = parts[2];
                            String receiverName = parts[3];
                            String time = parts[4];
                            String message = parts[5];

                            if (name.getText().equals(senderName)) {

                                MessageInfo messageInfo = new MessageInfo(senderId, senderName, receiverId, receiverName, time, message);

                                FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(getClass().getResource("message.fxml"));

                                try {
                                    HBox hBox = fxmlLoader.load();

                                    Message msg = fxmlLoader.getController();
                                    msg.generateMessage(messageInfo);
                                    messageVbox.getChildren().add(hBox);

//                                    sendThroughNetwork(messageInfo);


                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }

//                                if (data.contains("##close")){
//                                    // close connection
//                                }

                        } catch (SocketException se) {
                            se.printStackTrace();
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            serverListener.start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendThroughNetwork(MessageInfo info) {

//        try {
        String senderId = info.senderId;
        String senderName = info.senderName;
        String receiverId = info.receiverId;
        String receiverName = info.receiverName;
        String time = info.time;
        String message = info.messageText;

        String messagesToken = senderId + "##" + senderName + "##" + receiverId + "##" + receiverName + "##" + time + "##" + message;
//            writer.write(messagesToken + "\n");
//            writer.flush();

        DBUtil util = new DBUtil();
        boolean status = util.writeMessageInDatabase(info);
        System.out.println("Message send status" + status);

//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }


}