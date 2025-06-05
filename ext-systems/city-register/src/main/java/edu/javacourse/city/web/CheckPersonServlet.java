package edu.javacourse.city.web;

import edu.javacourse.city.dao.PersonCheckDao;
import edu.javacourse.city.dao.PoolConnectionBuilder;
import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.extension.PersonCheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name="CheckPersonServlet", urlPatterns = {"/checkPerson"})
public class CheckPersonServlet extends HttpServlet {

   private static final Logger logger = LoggerFactory.getLogger(CheckPersonServlet.class);
   private static PersonCheckDao checker;

    @Override
    public void init() throws ServletException {
        logger.info("CheckPersonServlet created");
        checker = new PersonCheckDao();
        checker.setConnectionBuilder(new PoolConnectionBuilder());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        PersonRequest preq = new PersonRequest();

        preq.setSurName(req.getParameter("surname"));
        preq.setGivenName(req.getParameter("givenName"));
        preq.setPatronymic(req.getParameter("patronymic"));
        preq.setDateOfBirth(LocalDate.parse(req.getParameter("dateOfBirth"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        preq.setStreetCode(Integer.parseInt(req.getParameter("streetCode")));
        preq.setBuilding(req.getParameter("building"));
        preq.setExtension(req.getParameter("extension"));
        preq.setApartment(req.getParameter("apartment"));

        try {
            PersonResponse presp = checker.checkPerson(preq);

            if (presp.isRegistered()){
                resp.getWriter().write("Registered");
            } else resp.getWriter().write("Not Registered");

        } catch (PersonCheckException e) {
            throw new RuntimeException(e);
        }

    }
}
