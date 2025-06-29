package edu.javacourse.student.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "jc_student_child")
public class StudentChild {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_child_id")
    private long studentChildId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "student_order_id")
    private StudentOrder studentOrder;


    @AssociationOverrides({
            @AssociationOverride(name = "address.street", joinColumns = @JoinColumn(name = "c_street_code")),
            @AssociationOverride(name = "registerOffice", joinColumns = @JoinColumn(name = "c_register_office_id"))
    })
    @AttributeOverrides({
            @AttributeOverride(name = "surname", column = @Column(name = "c_sur_name")),
            @AttributeOverride(name = "givenName", column = @Column(name = "c_given_name")),
            @AttributeOverride(name = "patronymic", column = @Column(name = "c_patronymic")),
            @AttributeOverride(name = "dateOfBirth", column = @Column(name = "c_date_of_birth")),

            @AttributeOverride(name = "address.building", column = @Column(name = "c_building")),
            @AttributeOverride(name = "address.extension", column = @Column(name = "c_extension")),
            @AttributeOverride(name = "address.apartment", column = @Column(name = "c_apartment")),
            @AttributeOverride(name = "address.postCode", column = @Column(name = "c_post_index")),

            @AttributeOverride(name = "certificateNumber", column = @Column(name = "c_certificate_number")),
            @AttributeOverride(name = "certificateDate", column = @Column(name = "c_certificate_date")),
    })
    @Embedded
    private Child child;

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public long getStudentChildId() {
        return studentChildId;
    }

    public void setStudentChildId(long studentChildId) {
        this.studentChildId = studentChildId;
    }

    public StudentOrder getStudentOrder() {
        return studentOrder;
    }

    public void setStudentOrder(StudentOrder studentOrder) {
        this.studentOrder = studentOrder;
    }

    //    @Column(name = "c_certificate_number")
//    private String certificateNumber;
//    private LocalDate issueDate;
//    private RegisterOffice issueDepartment;
//
//      public StudentChild() {
//    }
//
//    public String getCertificateNumber() {
//        return certificateNumber;
//    }
//
//    public void setCertificateNumber(String certificateNumber) {
//        this.certificateNumber = certificateNumber;
//    }
//
//    public LocalDate getIssueDate() {
//        return issueDate;
//    }
//
//    public void setIssueDate(LocalDate issueDate) {
//        this.issueDate = issueDate;
//    }
//
//    public RegisterOffice getIssueDepartment() {
//        return issueDepartment;
//    }
//
//    public void setIssueDepartment(RegisterOffice issueDepartment) {
//        this.issueDepartment = issueDepartment;
//    }

}
