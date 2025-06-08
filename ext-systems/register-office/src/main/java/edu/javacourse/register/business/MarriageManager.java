package edu.javacourse.register.business;

import edu.javacourse.register.dao.MarriageDao;
import edu.javacourse.register.dao.PersonDao;
import edu.javacourse.register.domain.MarriageCertificate;
import edu.javacourse.register.domain.Person;
import edu.javacourse.register.domain.PersonFemale;
import edu.javacourse.register.domain.PersonMale;
import edu.javacourse.register.view.MarriageRequest;
import edu.javacourse.register.view.MarriageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service("marriageManager")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MarriageManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageManager.class);

    @Autowired
    private MarriageDao marriageDao;

    @Autowired
    private PersonDao personDao;

    @Transactional
    public MarriageResponse findMarriageCertificate(MarriageRequest request) {
        LOGGER.info("MarriageManager.findMarriageCertificate called");

        Person p1 = createPerson(1);
        Person p2 = createPerson(1);

        long pId1 = personDao.addPerson(p1);
        long pId2 = personDao.addPerson(p2);

        MarriageCertificate cer = getMarriageCertificate();
        marriageDao.saveAndFlush(cer);
        marriageDao.findAll();

        return new MarriageResponse();
    }

    public void setMarriageDao(MarriageDao marriageDao) {
        this.marriageDao = marriageDao;
    }

    private MarriageCertificate getMarriageCertificate(){
        MarriageCertificate cer = new MarriageCertificate();
        cer.setIssueDate(LocalDate.now());
        cer.setNumber("12345");
        cer.setActive(true);
        
        List<Person> people = personDao.findPeople();
        for (Person person : people) {
            if (person instanceof PersonMale) cer.setHusband((PersonMale) person);
            else cer.setWife((PersonFemale) person);
        }

        return cer;
    }

    private Person createPerson(int gender) {
        Person p = gender == 1 ? new PersonMale() : new PersonFemale();
        p.setFirstName("fname_" + gender);
        p.setLastName("lname_" + gender);
        p.setPatronymic("patronumic_" + gender);
        p.setDateOfBirth(LocalDate.of(2025, 05, 25));
        return p;
    }
}
