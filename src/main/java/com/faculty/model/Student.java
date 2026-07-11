package com.faculty.model;

public class Student {
    private int id;
    private int userId;
    private String studentRegId;
    private String fullName;
    private String email;
    private String mobileNumber;
    private int degreeId;
    private String degreeName; // Useful for displaying in the table

    public Student(int id, int userId, String studentRegId, String fullName, String email, String mobileNumber, int degreeId, String degreeName) {
        this.id = id;
        this.userId = userId;
        this.studentRegId = studentRegId;
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.degreeId = degreeId;
        this.degreeName = degreeName;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getStudentRegId() {
        return studentRegId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public int getDegreeId() {
        return degreeId;
    }

    public String getDegreeName() {
        return degreeName;
    }
}