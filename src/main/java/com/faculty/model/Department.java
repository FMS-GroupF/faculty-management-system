package com.faculty.model;

public class Department {
    private int id;
    private String name;
    private String hodName;
    private int staffCount;

    public Department(int id, String name, String hodName, int staffCount) {
        this.id = id;
        this.name = name;
        this.hodName = hodName;
        this.staffCount = staffCount;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getHodName() { return hodName; }
    public int getStaffCount() { return staffCount; }

    // Setters (for updates)
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setHodName(String hodName) { this.hodName = hodName; }
    public void setStaffCount(int staffCount) { this.staffCount = staffCount; }
}