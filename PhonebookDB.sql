CREATE TABLE phonebook (
name varchar (30) NOT NULL,
surname varchar (100) NOT NULL,
phonenumber varchar (20) NOT NULL,
email varchar (30),
PRIMARY KEY(name, surname)
);

DROP TABLE phonebook;

INSERT INTO phonebook VALUES ('Astra', 'Brown', '+58125356', 'astra@astra.com');
INSERT INTO phonebook VALUES ('Mila', 'Smith', '+25685245', 'mila@mila.com');
INSERT INTO phonebook VALUES ('Dina', 'Green', '+25658552456', 'dina@dina.com');
INSERT INTO phonebook VALUES ('Marina', 'Sight', '+5874569558', 'marina@marina.com');
INSERT INTO phonebook VALUES ('Jane', 'Doe', '+2358644566', 'jane@jane.com');
INSERT INTO phonebook VALUES ('John', 'Snow', '+145878665', 'john@john.com');
INSERT INTO phonebook VALUES ('Steven', 'Stallone', '+7586522351', 'steven@steven.com');
INSERT INTO phonebook VALUES ('Mike', 'Rork', '+485696225262', 'mike@mike.com');
INSERT INTO phonebook VALUES ('Mike', 'Benks', '+23658925959', 'mikebenks@mike.com');
INSERT INTO phonebook VALUES ('Mindy', 'Black', '+547856329', 'mindy@mindy.com');
INSERT INTO phonebook VALUES ('Kevin', 'Kostner', '+12568895', 'kevin@kevin.com');
INSERT INTO phonebook VALUES ('Astrid', 'Siemens', '+895546866522', 'astrid@astrid.com');
INSERT INTO phonebook VALUES ('Frank', 'Sinatra', '+366595489', 'frank@frank.com');
INSERT INTO phonebook VALUES ('Alexander', 'Duma', '+897566225', 'alex@alex.com');
INSERT INTO phonebook VALUES ('Ian', 'Somerholder', '+589975566', 'ian@ian.com');
INSERT INTO phonebook VALUES ('Elena', 'Gilbert', '+587445662', 'elena@elena.com');
INSERT INTO phonebook VALUES ('Zed', 'Zeplin', '+456875296', 'zed@zed.com');
INSERT INTO phonebook VALUES ('Bobby', 'Brown', '+879566245', 'bobby@bobby.com');
INSERT INTO phonebook VALUES ('Samantha', 'Jones', '+12587456', 'samantha@samantha.com');
INSERT INTO phonebook VALUES ('Tom', 'Hiddleston', '+54235896', 'tom@tom.com');
INSERT INTO phonebook VALUES ('Jerry', 'Hersh', '+3658954566', 'jerry@jerry.com');

SELECT * FROM phonebook;

SELECT * FROM phonebook WHERE name LIKE '%na%' OR surname LIKE '%na%';