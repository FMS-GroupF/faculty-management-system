package com.faculty.model;

public class Lecturer {
    private int id;
    private int userId;
    private String fullName;
    private String email;
    private String mobileNumber;
    private int departmentId;
    private String departmentName; // For display in tables

    public Lecturer(int id, int userId, String fullName, String email, String mobileNumber, int departmentId, String departmentName) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    // Getters
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getMobileNumber() { return mobileNumber; }
    public int getDepartmentId() { return departmentId; }
    public String getDepartmentName() { return departmentName; }

    // Setters (useful for updates)
    public void setId(int id) { this.id = id; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
}