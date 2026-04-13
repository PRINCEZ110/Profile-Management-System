package com.profilemanagement.view;

import com.profilemanagement.controller.UserController;
import java.util.Scanner;

public class MainView {

    private UserController controller = new UserController();
    private Scanner scanner = new Scanner(System.in);

    public void startProgram() {
        System.out.println("\n=== PROFILE MANAGEMENT SYSTEM ===\n");

        boolean running = true;

        while (running) {
            showMenu();
            System.out.print("Enter choice (1-4): ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                addUser();
            } else if (choice.equals("2")) {
                viewAllUsers();
            } else if (choice.equals("3")) {
                viewUserById();
            } else if (choice.equals("4")) {
                System.out.println("Goodbye!");
                running = false;
            } else {
                System.out.println("Invalid choice!\n");
            }
        }

        scanner.close();
    }

    public void showMenu() {
        System.out.println("========== MENU ==========");
        System.out.println("1. Add User Profile");
        System.out.println("2. View All Users");
        System.out.println("3. View My Profile (by ID)");
        System.out.println("4. Exit");
        System.out.println("=========================");
    }

    public void addUser() {
        System.out.println("\n--- Add New User ---");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        controller.addUser(name, email, password);
        System.out.println();
    }

    public void viewAllUsers() {
        controller.showAllUsers();
    }

    public void viewUserById() {
        System.out.println("\n--- View User ---");
        System.out.print("Enter user ID: ");

        try {
            int id = Integer.parseInt(scanner.nextLine());
            controller.showUserById(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID!\n");
        }
    }

    public static void main(String[] args) {
        MainView view = new MainView();
        view.startProgram();
    }
}