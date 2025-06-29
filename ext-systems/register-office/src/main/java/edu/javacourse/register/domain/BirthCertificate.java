package edu.javacourse.register.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ro_birth_certificate")
public class BirthCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "birth_certificate_id")
    private long birthCertificateId;

    @Column(name = "number_certificate")
    private String number;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "father_id")
    private PersonMale father;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "mother_id")
    private PersonFemale mother;

    public long getBirthCertificateId() {
        return birthCertificateId;
    }

    public void setBirthCertificateId(long birthCertificateId) {
        this.birthCertificateId = birthCertificateId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PersonFemale getMother() {
        return mother;
    }

    public void setMother(PersonFemale mother) {
        this.mother = mother;
    }

    public PersonMale getFather() {
        return father;
    }

    public void setFather(PersonMale father) {
        this.father = father;
    }
}
