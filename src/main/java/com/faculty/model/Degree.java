package com.faculty.model;

public class Degree {
    private int id;
    private String name;
    private int departmentId;
    private String departmentName; // For display in tables

    public Degree(int id, String name, int departmentId, String departmentName) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public int getDepartmentId() { return departmentId; }
    public String getDepartmentName() { return departmentName; }

    // Setters (for updates)
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
}