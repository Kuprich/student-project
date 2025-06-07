package edu.javacourse.register.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("1")
public class PersonMale extends Person{
//    private List<MarriageCertificate> marriageCertificates;
//List
//    private BirthCertificate birthCertificate;
//
//    public List<MarriageCertificate> getMarriageCertificates() {
//        return marriageCertificates;
//    }
//
//    public void setMarriageCertificates(List<MarriageCertificate> marriageCertificates) {
//        this.marriageCertificates = marriageCertificates;
//    }
//
//    public BirthCertificate getBirthCertificate() {
//        return birthCertificate;
//    }
//
//    public void setBirthCertificate(BirthCertificate birthCertificate) {
//        this.birthCertificate = birthCertificate;
//    }
}

