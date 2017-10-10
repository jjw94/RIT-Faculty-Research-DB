USE 484PROJECT;

DROP TABLE IF EXISTS student;
CREATE TABLE student
(
	email varchar(30),
	password varchar(60),
	question varchar(100),
	answer varchar(100)
);

/*Password is local part of email hashed using MD5*/
/*Security answer is also local part of email hashed using MD5*/

INSERT INTO student(email, password, question, answer) VALUES("admin@gmail.com", "21232f297a57a5a743894a0e4a801fc3", "What's your username?", "21232f297a57a5a743894a0e4a801fc3");
INSERT INTO student(email, password, question, answer) VALUES("student@gmail.com", "cd73502828457d15655bbd7a63fb0bc8", "What's your username?", "cd73502828457d15655bbd7a63fb0bc8");
INSERT INTO student(email, password, question, answer) VALUES("guest@gmail.com", "084e0343a0486ff05530df6c705c8bb4", "What's your username?", "084e0343a0486ff05530df6c705c8bb4");
INSERT INTO student(email, password, question, answer) VALUES("test@gmail.com", "098f6bcd4621d373cade4e832627b4f6", "What's your username?", "098f6bcd4621d373cade4e832627b4f6");
INSERT INTO student(email, password, question, answer) VALUES("test2@gmail.com", "ad0234829205b9033196ba818f7a872b", "What's your username?", "ad0234829205b9033196ba818f7a872b");


ALTER TABLE faculty ADD question varchar(100);
ALTER TABLE faculty ADD answer varchar(100);

UPDATE faculty SET question = "What's your username?", answer = "5f47859188a602594556580532e814a3" WHERE email = "sjz@it.rit.edu";
UPDATE faculty SET question = "What's your username?", answer = "f4f6172eb26581952a70d7199bfd2ddb" WHERE email = "dsb@it.rit.edu";
