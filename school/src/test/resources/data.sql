//TRUNCATE TABLE course;
INSERT INTO course (`id`, `theme`, `hours`) VALUES
(1, 'Francais', 10),
(2, 'Math', 15),
(3, 'Anglais', 8);

//TRUNCATE TABLE student;
INSERT INTO student (`id`, `firstName`, `lastName`, `birthDate`, `mail`, `phoneNumber`, `address`) VALUES
(1, 'toto', 'otot', '2022-04-08', 'toto@gmail.com', '0000000000', '10 rue perdu, 2000 Introuvable'),
(2, 'jean', 'baptiste', '1999-04-08', 'jeanbaptiste@gmail.com', '0000010000', '5 rue perneau, 2000 Introuvable');
/*
//TRUNCATE TABLE teacher;
INSERT INTO teacher (`id`, `firstName`, `lastName`, `mail`, `phoneNumber`, `address`) VALUES
('firstName', 'lastName', 'mail', 'phoneNumber', 'address');

//TRUNCATE TABLE course_student;
INSERT INTO course_student (`course_id`, `student_id`) VALUES
(2, 1);

COMMIT;*/