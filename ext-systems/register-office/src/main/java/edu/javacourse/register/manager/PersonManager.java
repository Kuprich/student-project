package edu.javacourse.register.manager;

import edu.javacourse.register.domain.Person;
import edu.javacourse.register.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class PersonManager {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){

            session.getTransaction().begin();

            Person person = new Person();
            person.setFirstName("Петр");
            person.setLastName("Петров");

            long id = (long) session.save(person);

            System.out.println("id созданного объекта: " + id);

            session.getTransaction().commit();

            List<Person> people = session.createQuery("FROM Person", Person.class).list();
            people.forEach(System.out::println);

          //  session.getTransaction().commit();

        } catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            HibernateUtil.shutdown();
        }
//        SessionFactory sf = buildSessionFactory();
//        Session session = sf.openSession();
//
//        session.getTransaction().begin();
//
//        Person person = new Person();
//        person.setFirstName("Иван");
//        person.setLastName("Иванов");
//
//        long id = (long) session.save(person);
//        System.out.println(id);
//
//        session.getTransaction().commit();
//
//        Person person1 = session.get(Person.class, id);
//        System.out.println(person1);
//
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Person> cq = cb.createQuery(Person.class);
//        cq.select(cq.from(Person.class));
//        List<Person> people = session.createQuery(cq).getResultList();
//
//        people.forEach(System.out::println);
//
//        session.close();
//

    }

//    private static SessionFactory buildSessionFactory(){
//        try{
//            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                    .configure("hibernate.cfg.xml").build();
//
//            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
//
//            return  metadata.getSessionFactoryBuilder().build();
//        } catch (Exception e) {
//            System.err.println("Initial SessionFactory creation failed: " + e);
//            throw new ExceptionInInitializerError(e);
//        }
//    }
}
