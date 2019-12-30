-- Initialization script. Executes before application starts.
-- Users
INSERT INTO USERS (id, login, password, role, faculty, major, semester)
    VALUES (1, 'admin', 'admin', 'ADMINISTRATOR', null, null, 0);
INSERT INTO USERS (id, login, password, role, faculty, major, semester)
    VALUES (2, 'student', 'student', 'USER', 'Faculty of Law', 'Law', 3);
INSERT INTO USERS (id, login, password, role, faculty, major, semester)
    VALUES (3, 'student2', 'student2', 'USER', 'Faculty of Physics and Astronomy', 'Applied computer science', 1);
-- Messages
INSERT INTO MESSAGES (id, content) VALUES (1, 'Message 1');
INSERT INTO MESSAGES (id, content) VALUES (2, 'Message 2');
INSERT INTO MESSAGES (id, content) VALUES (3, 'Message 3');
INSERT INTO MESSAGES (id, content) VALUES (4, 'Message 4');
INSERT INTO MESSAGES (id, content) VALUES (5, 'Message 5');
-- Courses
INSERT INTO COURSES (id, name, description, start_date, end_date) VALUES (1, 'Course 1', 'Description 1', current_date, current_date + 15*7);
INSERT INTO COURSES (id, name, description, start_date, end_date) VALUES (2, 'Course 2', 'Description 2', current_date, current_date + 15*7);
INSERT INTO COURSES (id, name, description, start_date, end_date) VALUES (3, 'Course 3', 'Description 2', current_date, current_date + 15*7);
INSERT INTO COURSES (id, name, description, start_date, end_date) VALUES (4, 'Course 4', 'Description 4', current_date, current_date + 15*7);
-- course_user
INSERT INTO COURSE_USER (courses_id, users_id) VALUES (1, 2);
INSERT INTO COURSE_USER (courses_id, users_id) VALUES (2, 2);
INSERT INTO COURSE_USER (courses_id, users_id) VALUES (3, 2);
INSERT INTO COURSE_USER (courses_id, users_id) VALUES (4, 2);
INSERT INTO COURSE_USER (courses_id, users_id) VALUES (1, 3);
INSERT INTO COURSE_USER (courses_id, users_id) VALUES (2, 3);
--Activity
INSERT INTO ACTIVITIES (id, type, course_id) VALUES (1, 'LECTURE', 1);
INSERT INTO ACTIVITIES (id, type, course_id) VALUES (2, 'SEMINAR', 1);
INSERT INTO ACTIVITIES (id, type, course_id) VALUES (3, 'COMPUTER_LAB', 1);
INSERT INTO ACTIVITIES (id, type, course_id) VALUES (4, 'LECTURE', 2);
INSERT INTO ACTIVITIES (id, type, course_id) VALUES (5, 'SEMINAR', 3);
--Attendance list
--INSERT INTO ATTENDANCE_LIST (id) values (1);
--Attendance
--INSERT INTO ATTENDANCE (id, user_id, date_time, is_present, attendance_list_id) VALUES (1, 1, current_date, true, 1);
--INSERT INTO ATTENDANCE (id, user_id, date_time, is_present, attendance_list_id) VALUES (2, 1, current_date+1, true, 1);
--INSERT INTO ATTENDANCE (id, user_id, date_time, is_present, attendance_list_id) VALUES (3, 1, current_date+2, true, 1);
