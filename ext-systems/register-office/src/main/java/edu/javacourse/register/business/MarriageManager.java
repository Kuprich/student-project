package edu.javacourse.register.business;

import edu.javacourse.register.dao.MarriageDao;
import edu.javacourse.register.dao.PersonDao;
import edu.javacourse.register.rest.MarriageController;
import edu.javacourse.register.view.MarriageRequest;
import edu.javacourse.register.view.MarriageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("marriageManager")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MarriageManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageManager.class);

    @Autowired
    private MarriageDao marriageDao;

    @Autowired
    private PersonDao personDao;

    public MarriageResponse findMarriageCertificate(MarriageRequest request) {
        LOGGER.info("MarriageManager.findMarriageCertificate called");
        marriageDao.findMarriageCertificate(request);

        personDao.findPeople();

        return new MarriageResponse();
    }

    public void setMarriageDao(MarriageDao marriageDao) {
        this.marriageDao = marriageDao;
    }
}
