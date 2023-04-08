package com.example.chat.model;

import java.sql.*;


public class DBUtil {

    private Connection conn;

    public DBUtil() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/Chat_DB", "root", "");
    }

    public boolean logIn(String number, String password) {
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE mobile = ? AND password = ?");
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
            PreparedStatement stm = conn.prepareStatement("INSERT INTO user (name, mobile, password) VALUES (?, ?, ?)");
            stm.setString(1, name);
            stm.setString(2, mobile);
            stm.setString(3, password);
            stm.executeUpdate();
        //    stm.executeQuery();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
