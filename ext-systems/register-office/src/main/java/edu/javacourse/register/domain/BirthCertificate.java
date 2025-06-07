package edu.javacourse.register.domain;

import java.time.LocalDate;

public class BirthCertificate {
    private long birthCertificateId;
    private String number;
    private LocalDate issueDate;
    private Person person;
    private PersonMale mother;
    private PersonFemale father;

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

    public PersonMale getMother() {
        return mother;
    }

    public void setMother(PersonMale mother) {
        this.mother = mother;
    }

    public PersonFemale getFather() {
        return father;
    }

    public void setFather(PersonFemale father) {
        this.father = father;
    }
}
