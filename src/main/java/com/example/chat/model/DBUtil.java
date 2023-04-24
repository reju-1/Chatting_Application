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

    public List<ContactInfo> getFriendList(String id) { // find why result set is empty

        List<ContactInfo> list = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Chat_DB", "root", "");

            String sql = "SELECT * FROM `friends` WHERE `user_number` = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);

            ResultSet rs = stmt.executeQuery();
            System.out.println(rs.next());

            while (rs.next()) {
                String number = rs.getString(1);
                String name = rs.getString(2);
                System.out.println(name);

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

    public ArrayList<MessageInfo> getMessages(String senderId, String receiverId) {
        ArrayList<MessageInfo> messages = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Chat_DB", "root", "");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM `messages` WHERE sender = ? AND receiver = ?");
            stmt.setString(1, senderId);
            stmt.setString(2, receiverId);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                String sName = rs.getString(1);
                String sId = rs.getString(2);
                String rName = rs.getString(3);
                String rId = rs.getString(4);
                String time = rs.getString(5);
                String message = rs.getString(6);
                MessageInfo info = new MessageInfo(sName, sId, rName, rId, time, message);
                messages.add(info);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return messages;
    }

}
