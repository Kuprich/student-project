package edu.javacourse.register.dao;

import edu.javacourse.register.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


public class PersonDao {

    @PersistenceContext
    EntityManager entityManager;

    public List<Person> findPeople () {
        return entityManager.createNamedQuery("person.findPeople")
                .getResultList();
    }

    public List<Person> findPerson (long personId) {
        return (entityManager.createNamedQuery("person.findPerson"))
                .setParameter("personId", personId)
                .getResultList();
    }

    public long addPerson(Person person) {
        entityManager.persist(person);
        entityManager.flush();
        return person.getPersonId();
    }
}
