package edu.javacourse.student.service;

import edu.javacourse.student.domain.StudentOrder;
import edu.javacourse.student.repository.StudentOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentOrderService {

    @Autowired
    private StudentOrderRepository so_repository;

    @Transactional
    public List<StudentOrder> getStudentOrders(){
        return so_repository.findAll();
    }
}
