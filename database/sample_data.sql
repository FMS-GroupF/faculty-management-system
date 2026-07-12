USE fms_db;

-- Users (id 1-20)
INSERT INTO users (username, password, role) VALUES
    ('admin', 'admin123', 'Admin'),         -- 1
    ('kumar', 'kumar123', 'Student'),       -- 2
    ('sanga', 'sanga123', 'Lecturer'),      -- 3
    ('mithali', 'mithali123', 'Lecturer'),  -- 4
    ('nadeesha', 'nadeesha123', 'Student'), -- 5
    ('chamari', 'chamari123', 'Student'),   -- 6
    ('mahela', 'mahela123', 'Lecturer'),    -- 7
    ('kusal', 'kusal123', 'Lecturer'),      -- 8
    ('dilani', 'dilani123', 'Student'),     -- 9
    ('ruwan', 'ruwan123', 'Student'),       -- 10
    ('malinga', 'malinga123', 'Student'),   -- 11
    ('angelo', 'angelo123', 'Student'),     -- 12
    ('shanaka', 'shanaka123', 'Lecturer'),  -- 13
    ('dimuth', 'dimuth123', 'Lecturer'),    -- 14
    ('lasith', 'lasith123', 'Student'),     -- 15
    ('upul', 'upul123', 'Student'),         -- 16
    ('sachithra', 'sachithra123', 'Student'),-- 17
    ('hasini', 'hasini123', 'Student'),     -- 18
    ('roshan', 'roshan123', 'Lecturer'),    -- 19
    ('gayan', 'gayan123', 'Student');       -- 20

-- Departments (id 1-5)
INSERT INTO departments (name, hod_name, staff_count) VALUES
    ('Applied Computing', 'Kumar Sanga', 15),           -- 1
    ('Software Engineering', 'Mithali Raj', 17),        -- 2
    ('Computer Systems Engineering', 'Mahela J', 12),   -- 3
    ('Information Systems', 'Dilani Perera', 10),       -- 4
    ('Data Science', 'Roshan Fernando', 8);             -- 5

-- Degrees (id 1-6)
INSERT INTO degrees (name, department_id) VALUES
    ('Engineering Technology', 1),  -- 1
    ('Information Technology', 2),  -- 2
    ('Computer Science', 3),        -- 3
    ('Information Systems', 4),     -- 4
    ('Data Science', 5),            -- 5
    ('Cyber Security', 3);          -- 6

-- Lecturers (id 1-7)
-- user 3=sanga, 4=mithali, 7=mahela, 8=kusal, 13=shanaka, 14=dimuth, 19=roshan
INSERT INTO lecturers (user_id, full_name, email, mobile_number, department_id) VALUES
    (3, 'Kumar Sangakkara', 'kumars@kln.ac.lk', '0123456789', 2),      -- 1
    (4, 'Mithali Raj', 'mithalir@kln.ac.lk', '0987654321', 1),        -- 2
    (7, 'Mahela Jayawardene', 'mahelaj@kln.ac.lk', '0112223334', 3),  -- 3
    (8, 'Kusal Perera', 'kusalp@kln.ac.lk', '0115556667', 4),         -- 4
    (13, 'Shanaka Weerasinghe', 'shanakaw@kln.ac.lk', '0117778889', 1),-- 5
    (14, 'Dimuth Bandara', 'dimuthb@kln.ac.lk', '0119990001', 2),     -- 6
    (19, 'Roshan Fernando', 'roshanf@kln.ac.lk', '0112223335', 5);    -- 7

-- Students (id 1-12)
-- user 2=kumar, 5=nadeesha, 6=chamari, 9=dilani, 10=ruwan, 11=malinga,
-- 12=angelo, 15=lasith, 16=upul, 17=sachithra, 18=hasini, 20=gayan
INSERT INTO students (user_id, student_reg_id, full_name, email, mobile_number, degree_id) VALUES
    (2, 'ET/2022/011', 'Kumar Sangakkara', 'kumars-et22011@stu.kln.ac.lk', '0123456789', 1),      -- 1
    (5, 'IT/2022/015', 'Nadeesha Fernando', 'nadeeshaf-it22015@stu.kln.ac.lk', '0771234567', 2),  -- 2
    (6, 'CS/2022/023', 'Chamari Athapaththu', 'chamaria-cs22023@stu.kln.ac.lk', '0772345678', 3), -- 3
    (9, 'IS/2022/007', 'Dilani Perera', 'dilanip-is22007@stu.kln.ac.lk', '0773456789', 4),        -- 4
    (10, 'ET/2022/019', 'Ruwan Kalpage', 'ruwank-et22019@stu.kln.ac.lk', '0774567890', 1),        -- 5
    (11, 'CS/2022/031', 'Malinga Bandara', 'malingab-cs22031@stu.kln.ac.lk', '0775678901', 3),    -- 6
    (12, 'IT/2022/028', 'Angelo Mathews', 'angelom-it22028@stu.kln.ac.lk', '0776789012', 2),      -- 7
    (15, 'DS/2022/003', 'Lasith Malinga', 'lasithm-ds22003@stu.kln.ac.lk', '0777890123', 5),      -- 8
    (16, 'ET/2022/022', 'Upul Tharanga', 'upult-et22022@stu.kln.ac.lk', '0778901234', 1),         -- 9
    (17, 'CY/2022/009', 'Sachithra Senanayake', 'sachithras-cy22009@stu.kln.ac.lk', '0779012345', 6), -- 10
    (18, 'IS/2022/012', 'Hasini Perera', 'hasinip-is22012@stu.kln.ac.lk', '0770123456', 4),       -- 11
    (20, 'DS/2022/006', 'Gayan Wickramasinghe', 'gayanw-ds22006@stu.kln.ac.lk', '0771122334', 5); -- 12

-- Courses (id 1-14)
-- lecturer 1=sanga, 2=mithali, 3=mahela, 4=kusal, 5=shanaka, 6=dimuth, 7=roshan
INSERT INTO courses (course_code, course_name, credits, lecturer_id) VALUES
    ('ETEC 21062', 'OOP', 2, 1),                              -- 1
    ('CSCI 21052', 'Software Engineering', 2, 1),              -- 2
    ('ETEC 21022', 'Applied Computing', 2, 2),                 -- 3
    ('ISYS 31012', 'Database Systems', 3, 3),                  -- 4
    ('CSCI 31042', 'Data Structures & Algorithms', 3, 1),      -- 5
    ('ETEC 31032', 'Digital Electronics', 2, 4),                -- 6
    ('CSCI 41052', 'Machine Learning', 3, 2),                  -- 7
    ('ISYS 21022', 'Web Application Development', 2, 3),       -- 8
    ('CYSE 21012', 'Introduction to Cyber Security', 3, 5),    -- 9
    ('CYSE 31022', 'Network Security', 3, 5),                  -- 10
    ('DSCI 21012', 'Statistics for Data Science', 3, 7),       -- 11
    ('DSCI 31022', 'Data Mining', 3, 7),                       -- 12
    ('ETEC 41042', 'Embedded Systems', 3, 4),                  -- 13
    ('CSCI 21072', 'Operating Systems', 2, 6);                 -- 14

-- Enrollments
-- student 1=kumar,2=nadeesha,3=chamari,4=dilani,5=ruwan,6=malinga,
-- 7=angelo,8=lasith,9=upul,10=sachithra,11=hasini,12=gayan
INSERT INTO enrollments (student_id, course_id, grade) VALUES
    (1, 1, 'A+'), (1, 2, 'B'), (1, 3, 'A'), (1, 5, 'B+'),
    (2, 3, 'B+'), (2, 8, 'A-'), (2, 7, 'B'),
    (3, 4, 'A+'), (3, 5, 'B'), (3, 12, 'A-'),
    (4, 4, 'A'), (4, 8, 'B+'), (4, 11, 'A'),
    (5, 1, 'B'), (5, 6, 'A'), (5, 13, 'B+'),
    (6, 2, 'A-'), (6, 5, 'B+'), (6, 12, 'A'),
    (7, 3, 'A'), (7, 7, 'C+'), (7, 8, 'B'),
    (8, 11, 'A+'), (8, 12, 'A'),
    (9, 1, 'B'), (9, 6, 'B-'), (9, 13, 'A'),
    (10, 9, 'A'), (10, 10, 'B+'),
    (11, 4, 'A-'), (11, 8, 'B'),
    (12, 11, 'B+'), (12, 12, 'A');

-- Timetable data
INSERT INTO timetables (course_id, day_of_week, time_slot) VALUES
    (1, 'Monday', '08.00 - 10.00'),
    (2, 'Monday', '10.00 - 12.00'),
    (1, 'Tuesday', '08.00 - 10.00'),
    (3, 'Wednesday', '01.00 - 03.00'),
    (4, 'Thursday', '09.00 - 11.00'),
    (5, 'Tuesday', '10.00 - 12.00'),
    (6, 'Wednesday', '08.00 - 10.00'),
    (7, 'Thursday', '01.00 - 03.00'),
    (8, 'Friday', '09.00 - 11.00'),
    (9, 'Monday', '01.00 - 03.00'),
    (10, 'Tuesday', '08.00 - 10.00'),
    (11, 'Wednesday', '10.00 - 12.00'),
    (12, 'Thursday', '10.00 - 12.00'),
    (13, 'Friday', '01.00 - 03.00'),
    (14, 'Monday', '10.00 - 12.00'),
    (9, 'Wednesday', '01.00 - 03.00'),
    (11, 'Friday', '08.00 - 10.00');
