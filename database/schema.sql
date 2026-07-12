-- Drop the database if it already exists to start fresh
DROP DATABASE IF EXISTS fms_db;
CREATE DATABASE fms_db;
USE fms_db;

-- 1. Users Table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    role ENUM('Admin', 'Student', 'Lecturer') NOT NULL
);

-- 2. Departments Table
CREATE TABLE departments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    hod_name VARCHAR(100),
    staff_count INT DEFAULT 0
);

-- 3. Degrees Table
CREATE TABLE degrees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE SET NULL
);

-- 4. Lecturers Table
CREATE TABLE lecturers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    mobile_number VARCHAR(15),
    department_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE SET NULL
);

-- 5. Students Table
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT UNIQUE,
    student_reg_id VARCHAR(50) UNIQUE NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    mobile_number VARCHAR(15),
    degree_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (degree_id) REFERENCES degrees(id) ON DELETE SET NULL
);

-- 6. Courses Table
CREATE TABLE courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_code VARCHAR(20) UNIQUE NOT NULL,
    course_name VARCHAR(100) NOT NULL,
    credits INT NOT NULL,
    lecturer_id INT,
    FOREIGN KEY (lecturer_id) REFERENCES lecturers(id) ON DELETE SET NULL
);

-- 7. Enrollments Table
CREATE TABLE enrollments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_id INT,
    grade VARCHAR(5) DEFAULT 'N/A',
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

-- 8. Timetable Table
CREATE TABLE timetables (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT,
    day_of_week ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday') NOT NULL,
    time_slot VARCHAR(20) NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);
