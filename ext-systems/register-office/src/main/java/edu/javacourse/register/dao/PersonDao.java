package edu.javacourse.register.dao;

import edu.javacourse.register.domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PersonDao {

    EntityManager entityManager;

    public PersonDao() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Person> findPeople () {
        return entityManager.createNamedQuery("person.findPeople")
                .getResultList();
    }

    public List<Person> findPerson (long personId) {
        return (entityManager.createNamedQuery("person.findPerson"))
                .setParameter("personId", personId)
                .getResultList();
    }
}
