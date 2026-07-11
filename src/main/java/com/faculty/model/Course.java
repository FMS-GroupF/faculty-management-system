package com.faculty.model;

public class Course {
    private int id;
    private String courseCode;
    private String courseName;
    private int credits;
    private int lecturerId;
    private String lecturerName; // For display in tables

    public Course(int id, String courseCode, String courseName, int credits, int lecturerId, String lecturerName) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
    }

    // Getters
    public int getId() { return id; }
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getCredits() { return credits; }
    public int getLecturerId() { return lecturerId; }
    public String getLecturerName() { return lecturerName; }

    // Setters (for updates)
    public void setId(int id) { this.id = id; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setCredits(int credits) { this.credits = credits; }
    public void setLecturerId(int lecturerId) { this.lecturerId = lecturerId; }
    public void setLecturerName(String lecturerName) { this.lecturerName = lecturerName; }
}