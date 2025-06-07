package edu.javacourse.register.domain;

import java.time.LocalDate;

public class Passport {
    private long passportId;
    private String seria;
    private String number;
    private LocalDate issueDate;
    private String issueDepartment;

    public Passport() {
    }

    public Passport(long passportId, String seria, String number, LocalDate issueDate, String issueDepartment) {
        this.passportId = passportId;
        this.seria = seria;
        this.number = number;
        this.issueDate = issueDate;
        this.issueDepartment = issueDepartment;
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
