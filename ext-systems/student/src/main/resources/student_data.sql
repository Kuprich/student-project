INSERT INTO sr_university (university_name) VALUES ('First university');

INSERT INTO sr_faculty (university_id, faculty_name) VALUES (1, 'First faculty');

INSERT INTO sr_student (last_name, first_name, middle_name, date_of_birth, passport_seria, passport_number, passport_date)
VALUES ('Last', 'First', 'Middle', '2000-01-02', '1234', '123456', '2014-10-10');

INSERT INTO sr_student_document (document_number, document_date, expired_date, student_id, faculty_id, student_form)
VALUES ('123456', '2010-01-01', '2025-01-01', 1, 1, 0);