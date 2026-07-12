package com.faculty.controller;

import com.faculty.dao.LecturerDAO;
import com.faculty.dao.StudentDAO;
import com.faculty.dao.UserDAO;
import com.faculty.model.Student;
import com.faculty.model.User;
import com.faculty.view.AdminDashboardView;
import com.faculty.view.LecturerDashboardView;
import com.faculty.view.LoginView;
import com.faculty.view.StudentDashboardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView loginView;
    private UserDAO userDAO;

    public LoginController(LoginView loginView, UserDAO userDAO) {
        this.loginView = loginView;
        this.userDAO = userDAO;
        this.loginView.addLoginListener(new LoginListener());
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();
            String role = loginView.getSelectedRole();

            if (username.isEmpty() || password.isEmpty()) {
                loginView.showMessage("Please enter both username and password.");
                return;
            }

            // Verify with database
            User loggedInUser = userDAO.authenticateUser(username, password, role);

            if (loggedInUser != null) {
                loginView.showMessage("Login Successful! Welcome " + loggedInUser.getUsername());
                loginView.dispose(); // Close login window

                // Open the correct dashboard based on role
                if (loggedInUser.getRole().equals("Admin")) {
                    AdminDashboardView adminView = new AdminDashboardView(loggedInUser.getUsername());
                    new AdminController(adminView);
                    adminView.setVisible(true);
                } else if (loggedInUser.getRole().equals("Student")) {
                    StudentDAO studentDAO = new StudentDAO();
                    Student student = studentDAO.getStudentByUserId(loggedInUser.getId());
                    if (student != null) {
                        StudentDashboardView studentView = new StudentDashboardView(loggedInUser.getUsername());
                        new StudentController(studentView, loggedInUser.getId());
                        studentView.setVisible(true);
                    }
                } else if (loggedInUser.getRole().equals("Lecturer")) {
                    LecturerDashboardView lecturerView = new LecturerDashboardView(loggedInUser.getUsername());
                    new LecturerController(lecturerView, loggedInUser);
                    lecturerView.setVisible(true);
                } else {
                    // We will build Student and Lecturer dashboards later!
                    loginView.showMessage("Dashboard for " + loggedInUser.getRole() + " is under construction.");
                }

            } else {
                loginView.showMessage("Invalid Credentials or Incorrect Role selected.");
            }
        }
    }
}