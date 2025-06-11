package edu.javacourse.student.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "jc_university")
public class University {
    @Id
    @Column(name = "university_code")
    private long universityId;

    @Column(name = "university_name")
    private String universityName;

    public University(long universityId, String universityName) {
        this.universityId = universityId;
        this.universityName = universityName;
    }

    public University() {
    }

    public long getUniversityId() {
        return universityId;
    }

    public void setUniversityId(long universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @Override
    public String toString() {
        return "University{" +
                "universityId=" + universityId +
                ", universityName='" + universityName + '\'' +
                '}';
    }
}
