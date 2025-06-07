package edu.javacourse.register.domain;

import java.util.List;

public class PersonFemale extends Person{
    private List<MarriageCertificate> marriageCertificates;

    private BirthCertificate birthCertificate;

    public List<MarriageCertificate> getMarriageCertificates() {
        return marriageCertificates;
    }

    public void setMarriageCertificates(List<MarriageCertificate> marriageCertificates) {
        this.marriageCertificates = marriageCertificates;
    }

    public BirthCertificate getBirthCertificate() {
        return birthCertificate;
    }

    public void setBirthCertificate(BirthCertificate birthCertificate) {
        this.birthCertificate = birthCertificate;
    }
}
