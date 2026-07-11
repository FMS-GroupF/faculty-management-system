# Faculty Management System

A Java desktop application for managing faculty operations — students, lecturers, courses, departments, and degrees — built with Java Swing and backed by a MySQL database.

---

## Tech Stack

| Layer        | Technology                  |
| ------------ | --------------------------- |
| Language     | Java 25                     |
| UI Framework | Java Swing (custom themed)  |
| Build Tool   | Apache Maven                |
| Database     | MySQL (via XAMPP)           |
| JDBC Driver  | MySQL Connector/J 9.3.0     |
| IDE          | IntelliJ IDEA (recommended) |

---

## Prerequisites

Before running the project, make sure you have the following installed:

- **[IntelliJ IDEA](https://www.jetbrains.com/idea/)** (Community or Ultimate)
- **[XAMPP](https://www.apachefriends.org/)** (for the MySQL server)
- **JDK 21+** (JDK 25 recommended, matching the `pom.xml`)
- **Maven** (bundled with IntelliJ, no separate install needed)

---

## Database Setup (XAMPP)

1. **Start XAMPP** and turn on the **MySQL** module from the XAMPP Control Panel.

2. Open **phpMyAdmin** in your browser:

   ```
   http://localhost/phpmyadmin
   ```

3. Click the **SQL** tab and run the schema script first:
   - Open `database/schema.sql` and paste its contents into the SQL editor, then click **Go**.
   - This creates the `fms_db` database and all required tables.

4. _(Optional)_ Load sample data for testing:
   - Open `database/sample_data.sql`, paste its contents, and click **Go**.

> The app connects to MySQL on `localhost:3306` using the database `fms_db`.  
> Default XAMPP credentials are `root` with no password — check your DAO/connection config if you have a different setup.

---

## Running the Project in IntelliJ IDEA

1. **Clone or open the project:**
   - `File → Open` → select the `faculty-management-system` folder.
   - IntelliJ will detect the `pom.xml` and import it as a Maven project automatically.

2. **Let Maven sync dependencies:**
   - If prompted, click **Load Maven Project**.
   - Wait for the `mysql-connector-j` dependency to download (requires internet on first run).

3. **Make sure XAMPP MySQL is running** (see Database Setup above).

4. **Run the application:**
   - Navigate to `src/main/java/com/faculty/main/Main.java`
   - Right-click → **Run 'Main.main()'**

---

## Project Structure

```
faculty-management-system/
├── database/
│   ├── schema.sql          # Creates fms_db and all tables
│   └── sample_data.sql     # Optional seed data for testing
├── src/main/java/com/faculty/
│   ├── controller/         # Business logic controllers
│   ├── dao/                # Database access objects (JDBC queries)
│   ├── main/               # Entry point (Main.java)
│   ├── model/              # Data model classes
│   ├── util/               # Utility helpers
│   └── view/               # Swing UI views & AppTheme
└── pom.xml
```

## Database Schema Overview

| Table         | Description                                          |
| ------------- | ---------------------------------------------------- |
| `users`       | Login credentials and roles (Admin/Student/Lecturer) |
| `departments` | Faculty departments with HOD info                    |
| `degrees`     | Degree programs linked to departments                |
| `lecturers`   | Lecturer profiles linked to users                    |
| `students`    | Student profiles linked to users and degrees         |
| `courses`     | Courses with credits, assigned to a lecturer         |
| `enrollments` | Student–course enrolments with grades                |
| `timetables`  | Course schedule (day + time slot)                    |

---

## Default Login (after loading sample data)

| Role     | Username   | Password      |
| -------- | ---------- | ------------- |
| Admin    | `admin`    | `admin123`    |
| Student  | `kumar`    | `kumar123`    |
| Student  | `nadeesha` | `nadeesha123` |
| Student  | `chamari`  | `chamari123`  |
| Lecturer | `sanga`    | `sanga123`    |
| Lecturer | `mithali`  | `mithali123`  |
| Lecturer | `mahela`   | `mahela123`   |

> All sample accounts follow the pattern `<username><number>123`.  
> The full list of users is in `database/sample_data.sql`.
