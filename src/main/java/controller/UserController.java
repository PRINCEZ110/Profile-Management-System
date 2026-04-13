package com.profilemanagement.controller;

import com.profilemanagement.dao.UserDAO;
import com.profilemanagement.model.User;
import java.util.List;

public class UserController {

    private UserDAO userDAO = new UserDAO();

    public boolean addUser(String name, String email, String password) {
        if (name == null || name.isEmpty()) {
            System.out.println("✗ Name cannot be empty!");
            return false;
        }
        if (email == null || email.isEmpty()) {
            System.out.println("✗ Email cannot be empty!");
            return false;
        }
        if (password == null || password.isEmpty()) {
            System.out.println("✗ Password cannot be empty!");
            return false;
        }

        User user = new User(name, email, password);
        return userDAO.insertUser(user);
    }

    public void showAllUsers() {
        System.out.println("\n===== ALL USERS =====");
        List<User> users = userDAO.getAllUsers();

        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (User user : users) {
                System.out.println("---");
                System.out.println("ID: " + user.getId());
                System.out.println("Name: " + user.getName());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Password: " + user.getPassword());
            }
        }
        System.out.println("====================\n");
    }

    public void showUserById(int id) {
        System.out.println("\n===== USER PROFILE =====");
        User user = userDAO.getUserById(id);

        if (user != null) {
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Password: " + user.getPassword());
        }
        System.out.println("========================\n");
    }
}