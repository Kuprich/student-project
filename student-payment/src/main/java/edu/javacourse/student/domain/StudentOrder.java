package edu.javacourse.student.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "jc_student_order")
public class StudentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_order_id")
    private long studentOrderId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_order_status_id")
    private StudentOrderStatus status;

    @Column(name = "student_oder_date")
    private LocalDateTime studentOrderDate;

    @AssociationOverrides({
            @AssociationOverride(name = "address.street", joinColumns = @JoinColumn(name = "h_street_code")),
            @AssociationOverride(name = "passportOffice", joinColumns = @JoinColumn(name = "h_passport_office_id")),
            @AssociationOverride(name = "university", joinColumns = @JoinColumn(name = "h_university_id"))
    })
    @AttributeOverrides({
            @AttributeOverride(name = "surname", column = @Column(name = "h_sur_name")),
            @AttributeOverride(name = "givenName", column = @Column(name = "h_given_name")),
            @AttributeOverride(name = "patronymic", column = @Column(name = "h_patronymic")),
            @AttributeOverride(name = "dateOfBirth", column = @Column(name = "h_date_of_birth")),
            @AttributeOverride(name = "studentNumber", column = @Column(name = "h_student_number")),

            @AttributeOverride(name = "passportSeria", column = @Column(name = "h_passport_seria")),
            @AttributeOverride(name = "passportNumber", column = @Column(name = "h_passport_number")),
            @AttributeOverride(name = "issueDate", column = @Column(name = "h_passport_date")),

            @AttributeOverride(name = "address.building", column = @Column(name = "h_building")),
            @AttributeOverride(name = "address.extension", column = @Column(name = "h_extension")),
            @AttributeOverride(name = "address.apartment", column = @Column(name = "h_apartment")),
            @AttributeOverride(name = "address.postCode", column = @Column(name = "h_post_index")),
    })
    @Embedded
    private Adult husband;

    @AssociationOverrides({
            @AssociationOverride(name = "address.street", joinColumns = @JoinColumn(name = "w_street_code")),
            @AssociationOverride(name = "passportOffice", joinColumns = @JoinColumn(name = "w_passport_office_id")),
            @AssociationOverride(name = "university", joinColumns = @JoinColumn(name = "w_university_id"))
    })
    @AttributeOverrides({
            @AttributeOverride(name = "surname", column = @Column(name = "w_sur_name")),
            @AttributeOverride(name = "givenName", column = @Column(name = "w_given_name")),
            @AttributeOverride(name = "patronymic", column = @Column(name = "w_patronymic")),
            @AttributeOverride(name = "dateOfBirth", column = @Column(name = "w_date_of_birth")),
            @AttributeOverride(name = "studentNumber", column = @Column(name = "w_student_number")),

            @AttributeOverride(name = "passportSeria", column = @Column(name = "w_passport_seria")),
            @AttributeOverride(name = "passportNumber", column = @Column(name = "w_passport_number")),
            @AttributeOverride(name = "issueDate", column = @Column(name = "w_passport_date")),

            @AttributeOverride(name = "address.building", column = @Column(name = "w_building")),
            @AttributeOverride(name = "address.extension", column = @Column(name = "w_extension")),
            @AttributeOverride(name = "address.apartment", column = @Column(name = "w_apartment")),
            @AttributeOverride(name = "address.postCode", column = @Column(name = "w_post_index")),
    })
    @Embedded
    private Adult wife;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "register_office_id")
    private RegisterOffice registerOffice;

    @Column(name = "certificate_number")
    private String certificateNumber;

    @Column(name = "marriage_date")
    private LocalDate marriageDate;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE} , fetch = FetchType.LAZY, mappedBy = "studentOrder")
    private List<StudentChild> children;

    public List<StudentChild> getChildren() {
        return children;
    }

    public void setChildren(List<StudentChild> children) {
        this.children = children;
    }

    public RegisterOffice getRegisterOffice() {
        return registerOffice;
    }

    public void setRegisterOffice(RegisterOffice registerOffice) {
        this.registerOffice = registerOffice;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public LocalDate getMarriageDate() {
        return marriageDate;
    }

    public void setMarriageDate(LocalDate marriageDate) {
        this.marriageDate = marriageDate;
    }

    public void setStudentOrderId(long studentOrderId) {
        this.studentOrderId = studentOrderId;
    }

    public StudentOrderStatus getStatus() {
        return status;
    }

    public void setStatus(StudentOrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getStudentOrderDate() {
        return studentOrderDate;
    }

    public void setStudentOrderDate(LocalDateTime studentOrderDate) {
        this.studentOrderDate = studentOrderDate;
    }

    public Long getStudentOrderId() {
        return studentOrderId;
    }

    public void setStudentOrderId(Long studentOrderId) {
        this.studentOrderId = studentOrderId;
    }

    public Adult getHusband() {
        return husband;
    }

    public void setHusband(Adult husband) {
        this.husband = husband;
    }

    public Adult getWife() {
        return wife;
    }

    public void setWife(Adult wife) {
        this.wife = wife;
    }
}
