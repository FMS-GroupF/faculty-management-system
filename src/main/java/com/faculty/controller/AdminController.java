package com.faculty.controller;

import com.faculty.dao.*;
import com.faculty.model.*;
import com.faculty.view.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class AdminController {

    private AdminDashboardView view;
    private StudentDAO studentDAO;
    private LecturerDAO lecturerDAO;
    private CourseDAO courseDAO;
    private DepartmentDAO departmentDAO;
    private DegreeDAO degreeDAO;

    // Current mode
    private enum Mode {
        STUDENT, LECTURER, COURSE, DEPARTMENT, DEGREE
    }
    private Mode currentMode = Mode.STUDENT;

    public AdminController(AdminDashboardView view) {
        this.view = view;
        this.studentDAO = new StudentDAO();
        this.lecturerDAO = new LecturerDAO();
        this.courseDAO = new CourseDAO();
        this.departmentDAO = new DepartmentDAO();
        this.degreeDAO = new DegreeDAO();

        // --- 1. Attach Sidebar Listeners ---
        view.getBtnStudents().addActionListener(e -> switchMode(Mode.STUDENT));
        view.getBtnLecturers().addActionListener(e -> switchMode(Mode.LECTURER));
        view.getBtnCourses().addActionListener(e -> switchMode(Mode.COURSE));
        view.getBtnDepartments().addActionListener(e -> switchMode(Mode.DEPARTMENT));
        view.getBtnDegrees().addActionListener(e -> switchMode(Mode.DEGREE));

        // --- 2. Attach CRUD Button Listeners (they will delegate based on currentMode) ---
        view.getBtnAdd().addActionListener(e -> openAddForm());
        view.getBtnEdit().addActionListener(e -> openEditForm());
        view.getBtnDelete().addActionListener(e -> deleteEntity());

        // --- 3. Attach Logout Listener ---
        view.getBtnLogout().addActionListener(e -> {
            view.dispose();
            com.faculty.dao.UserDAO userDAO = new com.faculty.dao.UserDAO();
            com.faculty.view.LoginView loginView = new com.faculty.view.LoginView();
            new com.faculty.controller.LoginController(loginView, userDAO);
            loginView.setVisible(true);
        });

        // --- 3. Load initial data (Students) ---
        switchMode(Mode.STUDENT);
    }

    // ---------- Switch Mode ----------
    private void switchMode(Mode mode) {
        this.currentMode = mode;
        // Update highlighted sidebar button
        JButton activeBtn = getSidebarButton(mode);
        if (activeBtn != null) {
            view.highlightButton(activeBtn);
        }
        // Load the corresponding data
        loadData();
    }

    private JButton getSidebarButton(Mode mode) {
        switch (mode) {
            case STUDENT: return view.getBtnStudents();
            case LECTURER: return view.getBtnLecturers();
            case COURSE: return view.getBtnCourses();
            case DEPARTMENT: return view.getBtnDepartments();
            case DEGREE: return view.getBtnDegrees();
            default: return null;
        }
    }

    // ---------- Load Data Based on Current Mode ----------
    private void loadData() {
        view.clearTable();
        try {
            switch (currentMode) {
                case STUDENT:
                    System.out.println("Loading Students...");
                    view.setTableColumns(new String[]{"ID", "Full Name", "Student ID", "Degree", "Email", "Mobile"});
                    List<Student> students = studentDAO.getAllStudents();
                    System.out.println("Found " + students.size() + " students.");
                    for (Student s : students) {
                        view.getTableModel().addRow(new Object[]{
                                s.getId(),
                                s.getFullName(),
                                s.getStudentRegId(),
                                s.getDegreeName(),
                                s.getEmail(),
                                s.getMobileNumber()
                        });
                    }
                    break;

                case LECTURER:
                    System.out.println("Loading Lecturers...");
                    view.setTableColumns(new String[]{"ID", "Full Name", "Email", "Mobile", "Department"});
                    List<Lecturer> lecturers = lecturerDAO.getAllLecturers();
                    System.out.println("Found " + lecturers.size() + " lecturers.");
                    for (Lecturer l : lecturers) {
                        view.getTableModel().addRow(new Object[]{
                                l.getId(),
                                l.getFullName(),
                                l.getEmail(),
                                l.getMobileNumber(),
                                l.getDepartmentName()
                        });
                    }
                    break;

                case COURSE:
                    System.out.println("Loading Courses...");
                    view.setTableColumns(new String[]{"ID", "Course Code", "Course Name", "Credits", "Lecturer"});
                    List<Course> courses = courseDAO.getAllCourses();
                    System.out.println("Found " + courses.size() + " courses.");
                    for (Course c : courses) {
                        view.getTableModel().addRow(new Object[]{
                                c.getId(),
                                c.getCourseCode(),
                                c.getCourseName(),
                                c.getCredits(),
                                c.getLecturerName() != null ? c.getLecturerName() : "Not Assigned"
                        });
                    }
                    break;

                case DEPARTMENT:
                    System.out.println("Loading Departments...");
                    view.setTableColumns(new String[]{"ID", "Department Name", "Head of Department", "Staff Count"});
                    List<Department> departments = departmentDAO.getAllDepartments();
                    System.out.println("Found " + departments.size() + " departments.");
                    for (Department d : departments) {
                        view.getTableModel().addRow(new Object[]{
                                d.getId(),
                                d.getName(),
                                d.getHodName(),
                                d.getStaffCount()
                        });
                    }
                    break;

                case DEGREE:
                    System.out.println("Loading Degrees...");
                    view.setTableColumns(new String[]{"ID", "Degree Name", "Department"});
                    List<Degree> degrees = degreeDAO.getAllDegrees();
                    System.out.println("Found " + degrees.size() + " degrees.");
                    for (Degree d : degrees) {
                        view.getTableModel().addRow(new Object[]{
                                d.getId(),
                                d.getName(),
                                d.getDepartmentName() != null ? d.getDepartmentName() : "Not Assigned"
                        });
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "ERROR loading data: " + e.getMessage());
        }
    }

    // ---------- Get Selected Row ID ----------
    private int getSelectedId() {
        int selectedRow = view.getDataTable().getSelectedRow();
        if (selectedRow == -1) {
            return -1;
        }
        return (int) view.getTableModel().getValueAt(selectedRow, 0);
    }

    // ---------- Add Form ----------
    private void openAddForm() {
        switch (currentMode) {
            case STUDENT:
                Map<String, Integer> degrees = studentDAO.getDegreeOptions();
                StudentFormView studentForm = new StudentFormView(view, "Add New Student", degrees);
                studentForm.getBtnSave().addActionListener(e -> {
                    Student student = new Student(
                            0, 0,
                            studentForm.getRegId(),
                            studentForm.getFullName(),
                            studentForm.getEmail(),
                            studentForm.getMobile(),
                            studentForm.getSelectedDegreeId(),
                            null
                    );
                    if (studentDAO.addStudent(student, "password123")) {
                        JOptionPane.showMessageDialog(studentForm, "Student added! Default password: password123");
                        studentForm.dispose();
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(studentForm, "Failed to add student.");
                    }
                });
                studentForm.setVisible(true);
                break;

            case LECTURER:
                Map<String, Integer> depts = lecturerDAO.getDepartmentOptions();
                LecturerFormView lecturerForm = new LecturerFormView(view, "Add New Lecturer", depts);
                lecturerForm.getBtnSave().addActionListener(e -> {
                    String username = lecturerForm.getUsername();
                    String password = lecturerForm.getPassword();
                    if (username.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(lecturerForm, "Username and Password required.");
                        return;
                    }
                    Lecturer lecturer = new Lecturer(
                            0, 0,
                            lecturerForm.getFullName(),
                            lecturerForm.getEmail(),
                            lecturerForm.getMobile(),
                            lecturerForm.getSelectedDepartmentId(),
                            null
                    );
                    if (lecturerDAO.addLecturer(lecturer, username, password)) {
                        JOptionPane.showMessageDialog(lecturerForm, "Lecturer added! Username: " + username);
                        lecturerForm.dispose();
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(lecturerForm, "Failed to add lecturer.");
                    }
                });
                lecturerForm.setVisible(true);
                break;

            case COURSE:
                Map<String, Integer> lecturers = courseDAO.getLecturerOptions();
                CourseFormView courseForm = new CourseFormView(view, "Add New Course", lecturers);
                courseForm.getBtnSave().addActionListener(e -> {
                    Course course = new Course(
                            0,
                            courseForm.getCourseCode(),
                            courseForm.getCourseName(),
                            courseForm.getCredits(),
                            courseForm.getSelectedLecturerId(),
                            null
                    );
                    if (courseDAO.addCourse(course)) {
                        JOptionPane.showMessageDialog(courseForm, "Course added!");
                        courseForm.dispose();
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(courseForm, "Failed to add course.");
                    }
                });
                courseForm.setVisible(true);
                break;

            case DEPARTMENT:
                DepartmentFormView deptForm = new DepartmentFormView(view, "Add New Department");
                deptForm.getBtnSave().addActionListener(e -> {
                    Department dept = new Department(
                            0,
                            deptForm.getName(),
                            deptForm.getHodName(),
                            deptForm.getStaffCount()
                    );
                    if (departmentDAO.addDepartment(dept)) {
                        JOptionPane.showMessageDialog(deptForm, "Department added!");
                        deptForm.dispose();
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(deptForm, "Failed to add department.");
                    }
                });
                deptForm.setVisible(true);
                break;

            case DEGREE:
                Map<String, Integer> deptOptions = degreeDAO.getDepartmentOptions();
                DegreeFormView degreeForm = new DegreeFormView(view, "Add New Degree", deptOptions);
                degreeForm.getBtnSave().addActionListener(e -> {
                    Degree degree = new Degree(
                            0,
                            degreeForm.getName(),
                            degreeForm.getSelectedDepartmentId(),
                            null
                    );
                    if (degreeDAO.addDegree(degree)) {
                        JOptionPane.showMessageDialog(degreeForm, "Degree added!");
                        degreeForm.dispose();
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(degreeForm, "Failed to add degree.");
                    }
                });
                degreeForm.setVisible(true);
                break;
        }
    }

    // ---------- Edit Form ----------
    private void openEditForm() {
        int id = getSelectedId();
        if (id == -1) {
            JOptionPane.showMessageDialog(view, "Please select a record to edit.");
            return;
        }

        switch (currentMode) {
            case STUDENT: {
                Student student = studentDAO.getStudentById(id);
                if (student == null) return;
                Map<String, Integer> degrees = studentDAO.getDegreeOptions();
                StudentFormView form = new StudentFormView(view, "Edit Student", degrees);
                form.setStudentData(
                        student.getFullName(),
                        student.getStudentRegId(),
                        student.getEmail(),
                        student.getMobileNumber(),
                        student.getDegreeName()
                );
                form.getBtnSave().addActionListener(e -> {
                    Student updated = new Student(
                            student.getId(),
                            student.getUserId(),
                            student.getStudentRegId(),
                            form.getFullName(),
                            form.getEmail(),
                            form.getMobile(),
                            form.getSelectedDegreeId(),
                            null
                    );
                    if (studentDAO.updateStudent(updated)) {
                        JOptionPane.showMessageDialog(form, "Student updated!");
                        form.dispose();
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(form, "Update failed.");
                    }
                });
                form.setVisible(true);
                break;
            }

            case LECTURER: {
                Lecturer lecturer = lecturerDAO.getLecturerById(id);
                if (lecturer == null) return;
                Map<String, Integer> depts = lecturerDAO.getDepartmentOptions();
                LecturerFormView form = new LecturerFormView(view, "Edit Lecturer", depts);
                form.setEditMode(true);
                form.setLecturerData(
                        lecturer.getFullName(),
                        lecturer.getEmail(),
                        lecturer.getMobileNumber(),
                        lecturer.getDepartmentName()
                );
                form.getBtnSave().addActionListener(e -> {
                    Lecturer updated = new Lecturer(
                            lecturer.getId(),
                            lecturer.getUserId(),
                            form.getFullName(),
                            form.getEmail(),
                            form.getMobile(),
                            form.getSelectedDepartmentId(),
                            null
                    );
                    if (lecturerDAO.updateLecturer(updated)) {
                        JOptionPane.showMessageDialog(form, "Lecturer updated!");
                        form.dispose();
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(form, "Update failed.");
                    }
                });
                form.setVisible(true);
                break;
            }

            case COURSE: {
                Course course = courseDAO.getCourseById(id);
                if (course == null) return;
                Map<String, Integer> lecturers = courseDAO.getLecturerOptions();
                CourseFormView form = new CourseFormView(view, "Edit Course", lecturers);
                form.setCourseData(
                        course.getCourseCode(),
                        course.getCourseName(),
                        course.getCredits(),
                        course.getLecturerName()
                );
                form.getBtnSave().addActionListener(e -> {
                    Course updated = new Course(
                            course.getId(),
                            form.getCourseCode(),
                            form.getCourseName(),
                            form.getCredits(),
                            form.getSelectedLecturerId(),
                            null
                    );
                    if (courseDAO.updateCourse(updated)) {
                        JOptionPane.showMessageDialog(form, "Course updated!");
                        form.dispose();
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(form, "Update failed.");
                    }
                });
                form.setVisible(true);
                break;
            }

            case DEPARTMENT: {
                Department dept = departmentDAO.getDepartmentById(id);
                if (dept == null) return;
                DepartmentFormView form = new DepartmentFormView(view, "Edit Department");
                form.setDepartmentData(dept.getName(), dept.getHodName(), dept.getStaffCount());
                form.getBtnSave().addActionListener(e -> {
                    Department updated = new Department(
                            dept.getId(),
                            form.getName(),
                            form.getHodName(),
                            form.getStaffCount()
                    );
                    if (departmentDAO.updateDepartment(updated)) {
                        JOptionPane.showMessageDialog(form, "Department updated!");
                        form.dispose();
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(form, "Update failed.");
                    }
                });
                form.setVisible(true);
                break;
            }

            case DEGREE: {
                Degree degree = degreeDAO.getDegreeById(id);
                if (degree == null) return;
                Map<String, Integer> deptOptions = degreeDAO.getDepartmentOptions();
                DegreeFormView form = new DegreeFormView(view, "Edit Degree", deptOptions);
                form.setDegreeData(degree.getName(), degree.getDepartmentName());
                form.getBtnSave().addActionListener(e -> {
                    Degree updated = new Degree(
                            degree.getId(),
                            form.getName(),
                            form.getSelectedDepartmentId(),
                            null
                    );
                    if (degreeDAO.updateDegree(updated)) {
                        JOptionPane.showMessageDialog(form, "Degree updated!");
                        form.dispose();
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(form, "Update failed.");
                    }
                });
                form.setVisible(true);
                break;
            }
        }
    }

    // ---------- Delete Entity ----------
    private void deleteEntity() {
        int id = getSelectedId();
        if (id == -1) {
            JOptionPane.showMessageDialog(view, "Please select a record to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        boolean success = false;
        switch (currentMode) {
            case STUDENT:
                String regId = (String) view.getTableModel().getValueAt(view.getDataTable().getSelectedRow(), 2);
                success = studentDAO.deleteStudent(regId);
                break;
            case LECTURER:
                success = lecturerDAO.deleteLecturer(id);
                break;
            case COURSE:
                success = courseDAO.deleteCourse(id);
                break;
            case DEPARTMENT:
                success = departmentDAO.deleteDepartment(id);
                break;
            case DEGREE:
                success = degreeDAO.deleteDegree(id);
                break;
        }

        if (success) {
            JOptionPane.showMessageDialog(view, "Record deleted successfully!");
            loadData();
        } else {
            JOptionPane.showMessageDialog(view, "Delete failed.");
        }
    }
}
