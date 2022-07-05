//TRUNCATE TABLE course;
INSERT INTO course (`theme`, `hours`) VALUES
('Francais', 10),
('Math', 15),
('Anglais', 8);

//TRUNCATE TABLE student;
INSERT INTO student (`firstName`, `lastName`, `birthDate`, `mail`, `phoneNumber`, `address`) VALUES
('toto', 'otot', '2022-04-08', 'toto@gmail.com', '0000000000', '10 rue perdu, 2000 Introuvable'),
('jean', 'baptiste', '1999-04-08', 'jeanbaptiste@gmail.com', '0000010000', '5 rue perneau, 2000 Introuvable');

//TRUNCATE TABLE teacher;
INSERT INTO teacher (`firstName`, `lastName`, `mail`, `phoneNumber`, `address`) VALUES
('firstName', 'lastName', 'mail', 'phoneNumber', 'address');

//TRUNCATE TABLE school;
INSERT INTO school (`name`, `mail`, `phoneNumber`, `address`, `directorId`) VALUES
('name', 'mail', 'phoneNumber', 'address', 1);

COMMIT;