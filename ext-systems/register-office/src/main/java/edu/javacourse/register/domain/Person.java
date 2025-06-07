package edu.javacourse.register.domain;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private long personId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "lsat_name")
    private String lastName;
}
