package com.example.chat.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBUtil {

    public String logIn(String number, String password) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Chat_DB", "root", "");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE number = ? AND password = ?");
            stmt.setString(1, number);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean register(String mobile, String name, String password) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Chat_DB", "root", "");
            PreparedStatement stm = conn.prepareStatement("INSERT INTO user (name, number, password) VALUES (?, ?, ?)");
            stm.setString(1, name);
            stm.setString(2, mobile);
            stm.setString(3, password);
            stm.executeUpdate();
            //    stm.executeQuery();
            stm.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ContactInfo> getFriendList(String id) {

        List<ContactInfo> list = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Chat_DB", "root", "");

            String sql = "SELECT * FROM `friends` WHERE `user_number` = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("friend_name");
                String number = rs.getString("friend_number");
                list.add(new ContactInfo(number, name));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean writeMessageInDatabase(MessageInfo info) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Chat_DB", "root", "");
            String query = "INSERT INTO `messages` (`sender`, `receiver`, `time`, `message_text`) VALUES (?, ?, ?, ?)";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, info.senderId);
            stm.setString(2, info.receiverId);
            stm.setString(3, info.time);
            stm.setString(4, info.messageText);
            stm.executeUpdate();

            stm.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<MessageInfo> getMessages(String senderId, String senderName, String receiverId, String receiverName) {
        ArrayList<MessageInfo> messages = new ArrayList<>();
        try {

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Chat_DB", "root", "");
            //  PreparedStatement stmt = conn.prepareStatement("SELECT time, message_text FROM `messages` WHERE sender = ? AND receiver = ?");
            PreparedStatement stmt = conn.prepareStatement("SELECT sender, receiver,time, message_text FROM `messages` WHERE sender = ? AND receiver = ? or sender = ? AND receiver = ?");
            stmt.setString(1, senderId);
            stmt.setString(2, receiverId);

            stmt.setString(3, receiverId);
            stmt.setString(4, senderId);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                String sender = rs.getString("sender");
                String receiver = rs.getString("receiver");
                String time = rs.getString("time");
                String message = rs.getString("message_text");

                MessageInfo info;
                if (sender.equals(senderId)) {
                    info = new MessageInfo(receiverName, senderName, time, message);
                } else {
                    info = new MessageInfo(senderName, receiverName, time, message);
                }
                messages.add(info);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return messages;
    }

}
