#Deshira 19.05.2023
ALTER TABLE tbl_students ADD salt VARCHAR(255) NOT NULL AFTER password;

#Deshira 20.05.2023
ALTER TABLE tbl_admin ADD role VARCHAR(255) NOT NULL AFTER password;

ALTER TABLE tbl_students ADD role VARCHAR(255) DEFAULT 'student' AFTER salt;

INSERT INTO tbl_admin (first_name, last_name, username, email, password, role)
VALUES ('Admin1', 'Admin1', 'admin1', 'admin1@example.com', 'password123', 'admin');

INSERT INTO tbl_admin (first_name, last_name, username, email, password, role)
VALUES ('Admin2', 'Admin2', 'admin2', 'admin2@example.com', 'password123', 'admin');

INSERT INTO tbl_admin (first_name, last_name, username, email, password, role)
VALUES ('Admin3', 'Admin3', 'admin3', 'admin3@example.com', 'password123', 'admin');
