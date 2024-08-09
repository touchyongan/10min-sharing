
CREATE TABLE students (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	"name" varchar(200),
	age int
);

CREATE TABLE courses (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	"name" varchar(200)
);

CREATE TABLE student_course (
	student_id int8,
	course_id int8,
	CONSTRAINT student_cource_pkey PRIMARY KEY (student_id, course_id)
);


INSERT INTO students ("name", age)
VALUES ('Student A', 20),
	   ('Student B', 20),
	   ('Student C', 20),
	   ('Student D', 20),
	   ('Student E', 20),
	   ('Student F', 20),
	   ('Student G', 20),
	   ('Student H', 20),
	   ('Student I', 20),
	   ('Student J', 20),
	   ('Student K', 20);

INSERT INTO courses("name")
VALUES ('Java'),
	   ('PHP'),
	   ('C#'),
	   ('React'),
	   ('VueJS');

INSERT INTO student_course (student_id, course_id)
VALUES (1, 1),
	   (1, 2),
	   (1, 3),
	   (2, 1),
	   (2, 2),
	   (2, 4),
	   (3, 3),
	   (3, 1),
	   (4, 2),
	   (4, 3),
	   (5, 5),
	   (5, 3),
	   (5, 2),
	   (6, 5),
	   (6, 3),
	   (6, 2),
	   (7, 5),
	   (8, 3),
	   (9, 2);

CREATE TABLE products (
	id int8 NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	"name" varchar(200)
);

INSERT INTO products("name")
VALUES ('Product A'),
	   ('Product B'),
	   ('Product C'),
	   ('Product D'),
	   ('Product E');