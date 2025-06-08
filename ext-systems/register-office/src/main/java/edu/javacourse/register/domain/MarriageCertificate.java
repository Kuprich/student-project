package edu.javacourse.register.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ro_marriage_certificate")
@NamedQuery(name = "MarriageCertificate.findByNum", query =
    "SELECT mc FROM MarriageCertificate mc WHERE mc.number = :number")
public class MarriageCertificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "marriage_certificate_id")
    private long marriageCertificateId;

    @Column(name = "number_certificate")
    private String number;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "husband_id")
    private PersonMale husband;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "wife_id")
    private PersonFemale wife;

    @Column(name = "active")
    private boolean isActive;

    @Column(name = "end_date")
    private LocalDate endDate;


    public MarriageCertificate() {
    }

    public MarriageCertificate(long marriageCertificateId, String number, LocalDate issueDate, PersonMale husband, PersonFemale wife, boolean isActive, LocalDate endDate) {
        this.marriageCertificateId = marriageCertificateId;
        this.number = number;
        this.issueDate = issueDate;
        this.husband = husband;
        this.wife = wife;
        this.isActive = isActive;
        this.endDate = endDate;
    }

    public long getMarriageCertificateId() {
        return marriageCertificateId;
    }

    public void setMarriageCertificateId(long marriageCertificateId) {
        this.marriageCertificateId = marriageCertificateId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PersonMale getHusband() {
        return husband;
    }

    public void setHusband(PersonMale husband) {
        this.husband = husband;
    }

    public PersonFemale getWife() {
        return wife;
    }

    public void setWife(PersonFemale wife) {
        this.wife = wife;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
}
