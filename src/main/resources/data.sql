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