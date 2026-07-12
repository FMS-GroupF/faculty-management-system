package com.faculty.main;

import com.faculty.controller.LoginController;
import com.faculty.dao.UserDAO;
import com.faculty.view.LoginView;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserDAO userDAO = new UserDAO();
            LoginView loginView = new LoginView();
            new LoginController(loginView, userDAO);

            // Start the application by showing the login screen
            loginView.setVisible(true);
        });
    }
}