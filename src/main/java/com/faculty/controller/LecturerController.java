package com.faculty.controller;

import com.faculty.dao.LecturerDAO;
import com.faculty.dao.UserDAO;
import com.faculty.model.Lecturer;
import com.faculty.model.User;
import com.faculty.view.LecturerDashboardView;
import com.faculty.view.LoginView;

import javax.swing.*;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class LecturerController {
    private LecturerDashboardView view;
    private LecturerDAO lecturerDAO;
    private User loggedInUser;

    public LecturerController(LecturerDashboardView view, User loggedInUser) {
        this.view = view;
        this.loggedInUser = loggedInUser;
        this.lecturerDAO = new LecturerDAO();

        // Load lecturer data
        loadLecturerProfile();
        loadTimetable();
        loadTeachingCourses();

        // Logout
        view.getBtnLogout().addActionListener(e -> {
            view.dispose();
            LoginView loginView = new LoginView();
            new LoginController(loginView, new UserDAO());
            loginView.setVisible(true);
        });
    }

    // ---------- Load Profile ----------
    private void loadLecturerProfile() {
        Lecturer lecturer = lecturerDAO.getLecturerByUserId(loggedInUser.getId());

        if (lecturer == null) {
            JOptionPane.showMessageDialog(view, "Lecturer profile not found!");
            return;
        }

        view.getLblName().setText(lecturer.getFullName());
        view.getLblId().setText(String.valueOf(lecturer.getId()));
        view.getLblEmail().setText(lecturer.getEmail() != null ? lecturer.getEmail() : "N/A");
        view.getLblMobile().setText(lecturer.getMobileNumber() != null ? lecturer.getMobileNumber() : "N/A");
        view.getLblDepartment()
                .setText(lecturer.getDepartmentName() != null ? lecturer.getDepartmentName() : "Not Assigned");
    }

    // ---------- Load Timetable ----------
    private void loadTimetable() {
        String query = "SELECT t.day_of_week, t.time_slot, c.course_name FROM timetables t " +
                "JOIN courses c ON t.course_id = c.id " +
                "JOIN lecturers l ON c.lecturer_id = l.id " +
                "WHERE l.user_id = ? " +
                "ORDER BY FIELD(t.day_of_week, 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday'), t.time_slot";

        try (Connection conn = com.faculty.util.DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, loggedInUser.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                view.getTimetableModel().addRow(new Object[] {
                        rs.getString("day_of_week"),
                        rs.getString("time_slot"),
                        rs.getString("course_name")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Failed to load timetable: " + e.getMessage());
        }
    }

    // ---------- Load Teaching Courses ----------
    private void loadTeachingCourses() {
        String query = "SELECT course_code, course_name, credits FROM courses WHERE lecturer_id = " +
                "(SELECT id FROM lecturers WHERE user_id = ?)";

        try (Connection conn = com.faculty.util.DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, loggedInUser.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                view.getCoursesModel().addRow(new Object[] {
                        rs.getString("course_code"),
                        rs.getString("course_name"),
                        rs.getInt("credits")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Failed to load courses: " + e.getMessage());
        }
    }
}
