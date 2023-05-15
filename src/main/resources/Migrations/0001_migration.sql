create database projekti_knk;
use projekti_knk;

Create table user_account(
account_id int NOT NULL auto_increment,
firstname VARCHAR(50) NOT NULL,
lastname VARCHAR(50) NOT NULL,
username VARCHAR(50)  NOT NULL,
password VARCHAR(50) NOT NULL,
Primary key(account_id)
);

SELECT * FROM user_account;
insert into user_account(firstname,lastname,username,password)
values("Dafina","Balaj","dafinabalaj","db");