package edu.javacourse.register.dao;

import edu.javacourse.register.domain.MarriageCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface MarriageDao extends JpaRepository<MarriageCertificate, Long> {

//    private static final Logger LOGGER = LoggerFactory.getLogger(MarriageDao.class);
//
//    private EntityManager entityManager;
//
//    @Value("${test.value}")
//    private String test;
//
//    public MarriageCertificate findMarriageCertificate(MarriageRequest request){
//        LOGGER.info("MarriageDao.findMarriageCertificate called: {}", test);
//        return null;
//    }
//
//    public String getTest() {
//        return test;
//    }
//
//    public void setTest(String test) {
//        this.test = test;
//    }
}
