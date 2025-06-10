package edu.javacourse.student.servlet;

import edu.javacourse.student.domain.University;
import edu.javacourse.student.service.UniversityService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "UniversityListServlet", urlPatterns = {"/universityList"})
public class UniversityListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext ctx = getServletContext();
        WebApplicationContext webCtx = WebApplicationContextUtils.getWebApplicationContext(ctx);

        UniversityService service = webCtx.getBean(UniversityService.class);
        List<University> universities = service.getUniversities();

        req.setAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        req.setAttribute("universities", universities);

        getServletContext().getRequestDispatcher("/universityList").forward(req, resp);
    }
}
