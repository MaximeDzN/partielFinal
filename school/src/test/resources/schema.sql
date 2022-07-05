DROP TABLE IF EXISTS course CASCADE;
CREATE TABLE IF NOT EXISTS course(
  id REAL PRIMARY KEY AUTO_INCREMENT,
  theme VARCHAR(255) NULL,
  hours INTEGER NULL
);

DROP TABLE IF EXISTS student CASCADE;
CREATE TABLE IF NOT EXISTS student(
  id REAL PRIMARY KEY AUTO_INCREMENT,
  firstName VARCHAR(255) NULL,
  lastName VARCHAR(255) NULL,
  birthDate DATE NULL,
  mail VARCHAR(255) NULL,
  phoneNumber VARCHAR(255) NULL,
  address VARCHAR(255) NULL
);

DROP TABLE IF EXISTS teacher CASCADE;
CREATE TABLE IF NOT EXISTS teacher(
  id REAL PRIMARY KEY AUTO_INCREMENT,
  firstName VARCHAR(255) NULL,
  lastName VARCHAR(255) NULL,
  mail VARCHAR(255) NULL,
  phoneNumber VARCHAR(255) NULL,
  address VARCHAR(255) NULL
);

DROP TABLE IF EXISTS school CASCADE;
CREATE TABLE IF NOT EXISTS school(
  id REAL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NULL,
  mail VARCHAR(255) NULL,
  phoneNumber VARCHAR(255) NULL,
  address VARCHAR(255) NULL,
  directorId REAL NULL
);

DROP TABLE IF EXISTS student_course CASCADE;
CREATE TABLE IF NOT EXISTS student_course(
  idStudent REAL NOT NULL,
  idCourse REAL NOT NULL
);

ALTER TABLE student_course ADD CONSTRAINT student_course_primary PRIMARY KEY (idStudent,idCourse);
ALTER TABLE student_course ADD CONSTRAINT student_course_foreign_student FOREIGN KEY (idStudent) REFERENCES student (id) ON DELETE CASCADE;
ALTER TABLE student_course ADD CONSTRAINT student_course_foreign_course FOREIGN KEY (idCourse) REFERENCES course (id) ON DELETE CASCADE;

COMMIT;