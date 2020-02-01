-- Initialization script. Executes before application starts.
-- Users
INSERT INTO USERS (id, login, password, role, faculty, major, semester)
    VALUES (1, 'admin', 'admin', 'ADMINISTRATOR', null, null, 0);
INSERT INTO USERS (id, login, password, role, faculty, major, semester)
    VALUES (2, 'student', 'student', 'USER', 'Faculty of Law', 'Law', 3);
INSERT INTO USERS (id, login, password, role, faculty, major, semester) VALUES
    (3, 'student2', 'student2', 'USER', 'Faculty of Physics and Astronomy', 'Applied computer science', 1),
    (4, 'student3', 'student3', 'USER', 'Faculty of Physics and Astronomy', 'Applied computer science', 1),
    (5, 'student4', 'student4', 'USER', 'Faculty of Physics and Astronomy', 'Applied computer science', 1),
    (6, 'student5', 'student5', 'USER', 'Faculty of Physics and Astronomy', 'Applied computer science', 1),
    (7, 'student6', 'student6', 'USER', 'Faculty of Physics and Astronomy', 'Applied computer science', 1),
    (8, 'student7', 'student7', 'USER', 'Faculty of Physics and Astronomy', 'Applied computer science', 1),
    (9, 'student8', 'student8', 'USER', 'Faculty of Physics and Astronomy', 'Applied computer science', 1),
    (10, 'student9', 'student9', 'USER', 'Faculty of Physics and Astronomy', 'Applied computer science', 1);
-- Messages
INSERT INTO MESSAGES (id, content) VALUES (1, 'Message 1');
INSERT INTO MESSAGES (id, content) VALUES (2, 'Message 2');
INSERT INTO MESSAGES (id, content) VALUES (3, 'Message 3');
INSERT INTO MESSAGES (id, content) VALUES (4, 'Message 4');
INSERT INTO MESSAGES (id, content) VALUES (5, 'Message 5');
-- Courses
INSERT INTO COURSES (id, name, description, start_date, end_date) VALUES
    (1, 'Calculus', 'Basics of calculus - Calculating integrals and derivatives', current_date, current_date + 15*7),
    (2, 'Algorithms and Data structures', 'Sorting algorithms, lists, trees etc.', current_date, current_date + 15*7),
    (3, 'Probability', 'Basics of probability.', current_date, current_date + 15*7),
    (4, 'Discrete mathematics', 'Recurrence, graphs etc.', current_date, current_date + 15*7),
    (5, 'Linear algebra', 'Algebra', current_date, current_date + 15*7),
    (6, 'Databases', 'Course intended for absolute beginners. During this course you will learn basics of DML and DDL.', current_date, current_date + 15*7),
    (7, 'C++', 'C++ for beginners.', current_date, current_date + 15*7),
    (8, 'Team project', 'By attending this course, you will learn basic SCRUM principles.', current_date, current_date + 15*7);

-- course_user
INSERT INTO COURSE_USER (courses_id, users_id) VALUES
    (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10),
    (2, 1), (2, 2), (2, 3), (2, 4),
    (3, 5), (3, 6), (3, 7), (3, 8), (3, 9), (3, 10),
    (4, 1), (4, 2), (4, 3), (4, 4), (4, 5), (4, 6),
    (5, 7), (5, 8), (5, 9), (5, 10),
    (6, 7), (6, 8), (6, 9),
    (7, 7), (7, 8), (7, 9), (7, 10),
    (8, 7), (8, 8), (8, 9), (8, 10);
--Activity
INSERT INTO ACTIVITIES (id, type, course_id) VALUES (1, 'LECTURE', 1);
INSERT INTO ACTIVITIES (id, type, course_id) VALUES (2, 'SEMINAR', 1);
INSERT INTO ACTIVITIES (id, type, course_id) VALUES (3, 'COMPUTER_LAB', 1);
INSERT INTO ACTIVITIES (id, type, course_id) VALUES (4, 'LECTURE', 2);
INSERT INTO ACTIVITIES (id, type, course_id) VALUES (5, 'SEMINAR', 3);
--Attendance list
INSERT INTO ATTENDANCE_LIST (id, course_id) values (1, 2), (2, 1), (3, 3);
--Attendance
INSERT INTO ATTENDANCE (id, date_time, is_present, wants_to_be_signed, attendance_list_id, user_id) VALUES
    (1, current_date,	true,	false,	1,	1),
    (2,	current_date,	true,	false,	1,	2),
    (3,	current_date,	false,	false,	1,	3),
    (4,	current_date+1,	true,	false,	2,	1),
    (5,	current_date+1,	true,	false,	2,	2),
    (6,	current_date+1,	false,	false,	2,	3),
    (8,	current_date+2,	false,	false,	3,	5),
    (9,	current_date+2,	false,	false,	3,	6),
    (10, current_date+2,	false,	false,	3,	7),
    (11, current_date+2,	false,	false,	3,	8),
    (12, current_date+2,	false,	false,	3,	9);
