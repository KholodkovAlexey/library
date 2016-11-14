--liquibase formatted sql

DROP TABLE books IF EXISTS;
DROP TABLE users IF EXISTS;

CREATE TABLE users (id INT NOT NULL AUTO_INCREMENT,
name VARCHAR(40) NOT NULL UNIQUE, password VARCHAR(40) NOT NULL,
PRIMARY KEY (id));

CREATE TABLE books (isn VARCHAR(40) NOT NULL UNIQUE, 
author VARCHAR(40) NOT NULL,title VARCHAR(40) NOT NULL, 
borrower_id INT,FOREIGN KEY (borrower_id) REFERENCES users(id));

INSERT INTO users (name, password) VALUES ('Jenny','pass0');

INSERT INTO users (name, password) VALUES ('John','pass1');

INSERT INTO users (name, password) VALUES ('James','pass2');

INSERT INTO users (name, password) VALUES ('Jerry','pass3');

INSERT INTO users (name, password) VALUES ('Carl','pass4');

INSERT INTO books (isn, author, title, borrower_id)
VALUES ('1486-2549-2114-5686','Author1','Title1',2);

INSERT INTO books (isn, author, title, borrower_id)
VALUES ('1486-2549-2114-5687','Author1','Title2',2);

INSERT INTO books (isn, author, title, borrower_id)
VALUES ('1486-2549-2114-5688','Author1','Title3',null);

INSERT INTO books (isn, author, title, borrower_id)
VALUES ('0000-0000-0001','TestAuthor','TestTitle1',null);

INSERT INTO books (isn, author, title, borrower_id)
VALUES ('0000-0000-0002','TestAuthor','TestTitle2',null);

INSERT INTO books (isn, author, title, borrower_id)
VALUES ('0000-0000-0003','TestAuthor','TestTitle3',null);

INSERT INTO books (isn, author, title, borrower_id)
VALUES ('0000-0000-0004','TestAuthor','TestTitle4',null);

INSERT INTO books (isn, author, title, borrower_id)
VALUES ('0000-0000-0005','TestAuthor','TestTitle5',null);

INSERT INTO books (isn, author, title, borrower_id)
VALUES ('0000-0000-0006','TestAuthor','TestTitle6',null);

INSERT INTO books (isn, author, title, borrower_id)
VALUES ('0000-0000-0007','TestAuthor','TestTitle7',null);

INSERT INTO books (isn, author, title, borrower_id) 
VALUES ('0000-0000-0008','TestAuthor','TestTitle8',null);