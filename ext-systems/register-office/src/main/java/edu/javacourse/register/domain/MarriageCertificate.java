package edu.javacourse.register.domain;

import java.time.LocalDate;

public class MarriageCertificate {

    private long marriageCertificateId;
    private String number;
    private String issueDate;
    private PersonMale husband;
    private PersonFemale wife;
    private boolean isActive;
    private LocalDate endDate;


    public MarriageCertificate() {
    }

    public MarriageCertificate(long marriageCertificateId, String number, String issueDate, PersonMale husband, PersonFemale wife, boolean isActive, LocalDate endDate) {
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

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
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
}
