package com.example.chat.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DBUtil {

    public boolean logIn(String number, String password) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Chat_DB", "root", "");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE number = ? AND password = ?");
            stmt.setString(1, number);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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


}
