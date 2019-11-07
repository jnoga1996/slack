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