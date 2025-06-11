package edu.javacourse.student.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jc_order_status")
public class StudentOrderStatus {
    @Id
    @Column(name = "status_id")
    private long statusId;

    @Column(name = "status_name")
    private String statusName;
}