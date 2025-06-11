package edu.javacourse.student.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "jc_passport_office")
public class PassportOffice {
    @Id
    @Column(name = "p_office_id")
    private long officeId;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "p_office_area_id")
    private CountryArea countryArea;

    @Column(name = "p_office_name")
    private String officeName;

    public PassportOffice(long officeId, CountryArea countryArea, String officeName) {
        this.officeId = officeId;
        this.countryArea = countryArea;
        this.officeName = officeName;
    }

    public PassportOffice() {
    }

    public long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(long officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public CountryArea getCountryArea() {
        return countryArea;
    }

    public void setCountryArea(CountryArea countryArea) {
        this.countryArea = countryArea;
    }
}
