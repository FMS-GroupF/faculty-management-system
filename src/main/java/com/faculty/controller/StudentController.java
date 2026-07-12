package com.faculty.controller;

import com.faculty.dao.StudentDAO;
import com.faculty.model.Student;
import com.faculty.view.StudentDashboardView;

import javax.swing.*;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class StudentController {
    private StudentDashboardView view;
    private StudentDAO studentDAO;
    private int userId;

    public StudentController(StudentDashboardView view, int userId) {
        this.view = view;
        this.studentDAO = new StudentDAO();
        this.userId = userId;
        loadStudentData();
    }

    private void loadStudentData() {
        try {
            // 1. Load Profile
            Student student = studentDAO.getStudentByUserId(userId);
            if (student != null) {
                view.getLblName().setText(student.getFullName());
                view.getLblRegId().setText(student.getStudentRegId());
                view.getLblDegree().setText(student.getDegreeName() != null ? student.getDegreeName() : "Not Assigned");
                view.getLblEmail().setText(student.getEmail());
                view.getLblMobile().setText(student.getMobileNumber());

                // 2. Load Timetable
                List<Object[]> timetable = studentDAO.getTimetableByStudentId(student.getId());
                for (Object[] row : timetable) {
                    view.getTimetableModel().addRow(row);
                }

                // 3. Load Courses with Grades
                List<Object[]> courses = studentDAO.getEnrolledCoursesWithGrades(student.getId());
                for (Object[] row : courses) {
                    view.getCoursesModel().addRow(row);
                }
            } else {
                JOptionPane.showMessageDialog(view, "Student profile not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error loading data: " + e.getMessage());
        }
    }
}
