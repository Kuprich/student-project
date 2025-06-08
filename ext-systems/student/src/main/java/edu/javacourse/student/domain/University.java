package edu.javacourse.student.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sr_university")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "university_id")
    private long universityId;

    @Column(name = "university_name")
    private String universityName;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "university")
    List<Faculty> faculties;

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

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }
}
