-- Добавляем людей
INSERT INTO ro_person (gender, first_name, last_name, patronymic, date_of_birth) VALUES
(1, 'Иван', 'Иванов', 'Иванович', '1980-05-15'),
(2, 'Мария', 'Иванова', 'Петровна', '1982-07-22'),
(1, 'Алексей', 'Петров', 'Сергеевич', '1990-01-10'),
(2, 'Елена', 'Сидорова', 'Александровна', '1992-11-30'),
(1, 'Сергей', 'Смирнов', 'Дмитриевич', '2005-03-18'),
(2, 'Ольга', 'Кузнецова', 'Владимировна', '2007-09-05');

-- Добавляем паспорта
INSERT INTO ro_passport (person_id, seria, number, issue_date, issue_department) VALUES
(1, '4501', '123456', '2010-05-20', 'ОУФМС России по г. Москве'),
(2, '4502', '654321', '2011-06-15', 'ОУФМС России по г. Москве'),
(3, '4503', '789012', '2015-03-10', 'ОУФМС России по г. Санкт-Петербург'),
(4, '4504', '210987', '2016-04-25', 'ОУФМС России по г. Санкт-Петербург');

-- Добавляем свидетельства о рождении
INSERT INTO ro_birth_certificate (number_certificate, issue_date, person_id, mother_id, father_id) VALUES
('I-МЮ №123456', '2005-03-25', 5, 2, 1),
('I-МЮ №654321', '2007-09-15', 6, 4, 3);

-- Добавляем свидетельства о браке
INSERT INTO ro_marriage_certificate (number_certificate, issue_date, husband_id, wife_id, active, end_date) VALUES
('III-БР №123456', '2003-08-10', 1, 2, true, null),
('III-БР №654321', '2014-05-20', 3, 4, false, '2020-12-15');