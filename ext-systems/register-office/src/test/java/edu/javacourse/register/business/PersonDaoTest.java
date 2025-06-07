package edu.javacourse.register.business;


import edu.javacourse.register.domain.Person;
import edu.javacourse.register.domain.PersonFemale;
import edu.javacourse.register.domain.PersonMale;
import org.junit.Test;

import java.util.List;

public class PersonDaoTest {

    @Test
    public void findPeople(){
        PersonDao dao = new PersonDao();
        List<Person> people = dao.findPeople();

        people.forEach(person -> {
            System.out.println(person.getFirstName());
            System.out.println(person.getClass().getName());
            System.out.println(person.getPassports().size());
            System.out.println(person.getBirthCertificate());
            if (person instanceof PersonMale) {
                System.out.println(((PersonMale) person).getBirthCertificates().size());
                System.out.println(((PersonMale) person).getMarriageCertificates().size());
            } else {
                System.out.println(((PersonFemale) person).getBirthCertificates().size());
                System.out.println(((PersonFemale) person).getMarriageCertificates().size());
            }
        });
    }
}