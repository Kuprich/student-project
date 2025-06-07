package edu.javacourse.register.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ro_passport")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passport_id")
    private long passportId;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(name="seria")
    private String seria;

    @Column(name = "number")
    private String number;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "issue_department")
    private String issueDepartment;

    public Passport() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public long getPassportId() {
        return passportId;
    }

    public void setPassportId(long passportId) {
        this.passportId = passportId;
    }

    public String getSeria() {
        return seria;
    }

    public void setSeria(String seria) {
        this.seria = seria;
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

    public String getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(String issueDepartment) {
        this.issueDepartment = issueDepartment;
    }
}
