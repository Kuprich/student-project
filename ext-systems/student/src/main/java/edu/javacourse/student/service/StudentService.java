package edu.javacourse.student.service;

import edu.javacourse.student.dao.StudentRepository;
import edu.javacourse.student.domain.Student;
import edu.javacourse.student.domain.StudentDocument;
import edu.javacourse.student.view.StudentRequest;
import edu.javacourse.student.view.StudentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    @Transactional
    public List<StudentResponse> getStudentInfo(StudentRequest request) {
        List<Student> students = studentRepository.findStudent(request.getLastName(), request.getFirstName(),
                request.getMiddleName(), request.getDateOfBirth(), request.getPassportSeria(),
                request.getPassportNumber(), request.getPassportDate());

        if (students.isEmpty()) return Collections.EMPTY_LIST;
        List<StudentDocument> docs = students.get(0).getStudentDocuments();

        List<StudentResponse> result = docs.stream().map(this::getResponse).collect(Collectors.toList());

        return result;
    }

    private StudentResponse getResponse(StudentDocument doc) {
        StudentResponse sr = new StudentResponse();
        sr.setDocumentNumber(doc.getDocumentNumber());
        sr.setDocumentDate(doc.getDocumentDate());
        sr.setExpiredDate(doc.getExpiredDate());
        sr.setFacultyName(doc.getFaculty().getFacultyName());
        sr.setUniversityName(doc.getFaculty().getUniversity().getUniversityName());
        sr.setStudentForm(doc.getStudentForm().toString());

        return sr;
    }

}
