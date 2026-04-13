package com.profilemanagement.dao;

import com.profilemanagement.model.User;
import com.profilemanagement.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public boolean insertUser(User user) {
        String sql = "INSERT INTO profile (name, email, password) VALUES (?, ?, ?)";
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                System.out.println("✗ No connection!");
                return false;
            }

            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("✓ User added successfully!");
                return true;
            }

        } catch (SQLException e) {
            System.out.println("✗ Error adding user: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (connection != null) DBConnection.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM profile";
        List<User> users = new ArrayList<>();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                System.out.println("✗ No connection!");
                return users;
            }

            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");

                User user = new User(id, name, email, password);
                users.add(user);
            }

            System.out.println("✓ Found " + users.size() + " users!");

        } catch (SQLException e) {
            System.out.println("✗ Error fetching users: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (connection != null) DBConnection.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM profile WHERE id = ?";
        User user = null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                System.out.println("✗ No connection!");
                return null;
            }

            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");

                user = new User(userId, name, email, password);
                System.out.println("✓ User found!");
            } else {
                System.out.println("✗ User not found!");
            }

        } catch (SQLException e) {
            System.out.println("✗ Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (connection != null) DBConnection.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }
}