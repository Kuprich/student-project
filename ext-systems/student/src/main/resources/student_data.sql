-- Университеты
INSERT INTO sr_university (university_name) VALUES
('Московский государственный университет'),
('Санкт-Петербургский политехнический университет'),
('Новосибирский государственный университет'),
('Казанский федеральный университет'),
('Уральский федеральный университет'),
('Томский политехнический университет'),
('Дальневосточный федеральный университет'),
('Южный федеральный университет'),
('Сибирский федеральный университет'),
('Национальный исследовательский ядерный университет МИФИ');

-- Факультеты (каждому университету по 1 факультету)
INSERT INTO sr_faculty (university_id, faculty_name) VALUES
(1, 'Механико-математический факультет'),
(2, 'Факультет компьютерных технологий'),
(3, 'Физический факультет'),
(4, 'Химический факультет'),
(5, 'Экономический факультет'),
(6, 'Факультет автоматики и вычислительной техники'),
(7, 'Факультет биологии'),
(8, 'Юридический факультет'),
(9, 'Факультет филологии'),
(10, 'Факультет ядерной физики');

-- Студенты
INSERT INTO sr_student (last_name, first_name, middle_name, date_of_birth, passport_seria, passport_number, passport_date) VALUES
('Иванов', 'Иван', 'Иванович', '2000-05-15', '1234', '567890', '2016-03-20'),
('Петров', 'Пётр', 'Петрович', '2001-07-22', '2345', '678901', '2017-04-12'),
('Сидоров', 'Сергей', 'Сергеевич', '1999-11-30', '3456', '789012', '2015-09-05'),
('Кузнецова', 'Анна', 'Владимировна', '2000-02-14', '4567', '890123', '2016-07-18'),
('Смирнов', 'Дмитрий', 'Алексеевич', '2001-09-08', '5678', '901234', '2017-01-25'),
('Васильева', 'Елена', 'Олеговна', '1999-12-03', '6789', '012345', '2015-11-11'),
('Попов', 'Александр', 'Дмитриевич', '2000-04-19', '7890', '123456', '2016-05-30'),
('Фёдорова', 'Мария', 'Игоревна', '2001-08-27', '8901', '234567', '2017-02-14'),
('Николаев', 'Андрей', 'Сергеевич', '1999-10-10', '9012', '345678', '2015-08-22'),
('Орлова', 'Ольга', 'Викторовна', '2000-01-05', '0123', '456789', '2016-12-01');

-- Документы студентов (каждому студенту по 1 документу)
INSERT INTO sr_student_document (document_number, document_date, expired_date, student_id, faculty_id, student_form) VALUES
('МГУ123', '2020-09-01', '2024-06-30', 1, 1, 0),
('СПбПУ456', '2020-09-01', '2024-06-30', 2, 2, 0),
('НГУ789', '2019-09-01', '2023-06-30', 3, 3, 1),
('КФУ012', '2020-09-01', '2024-06-30', 4, 4, 0),
('УрФУ345', '2021-09-01', '2025-06-30', 5, 5, 0),
('ТПУ678', '2019-09-01', '2023-06-30', 6, 6, 1),
('ДВФУ901', '2020-09-01', '2024-06-30', 7, 7, 0),
('ЮФУ234', '2021-09-01', '2025-06-30', 8, 8, 0),
('СФУ567', '2019-09-01', '2023-06-30', 9, 9, 1),
('МИФИ890', '2020-09-01', '2024-06-30', 10, 10, 0);