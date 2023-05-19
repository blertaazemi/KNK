create database projekti_knk;
use projekti_knk;

tabelat

CREATE TABLE tbl_students (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE tbl_bursa (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  nota_mesatare DECIMAL(3,2) NOT NULL,
  description TEXT NOT NULL,
  amount DECIMAL(4,2) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE tbl_aplikimet (
  id INT NOT NULL AUTO_INCREMENT,
  student_id INT NOT NULL,
  bursa_id INT NOT NULL,
  viti_studimit INT NOT NULL,
  nota_mesatare DECIMAL(3,2) NOT NULL,
  transkripta LONGBLOB,
  date_submitted DATETIME NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (student_id) REFERENCES tbl_students(id),
  FOREIGN KEY (bursa_id) REFERENCES tbl_bursa(id),
);


CREATE TABLE tbl_admin (
  id INT NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

query sa me testu
INSERT INTO tbl_students (first_name, last_name, username, email, password)
VALUES ('John', 'Doe', 'johndoe', 'johndoe@example.com', 'password123');

INSERT INTO tbl_students (first_name, last_name, username, email, password)
VALUES ('Johnny', 'Doer', 'johnnydoer', 'johnnydoer@example.com', 'password123');
