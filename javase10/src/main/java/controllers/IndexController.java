package controllers;

import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/")
public class IndexController extends HttpServlet {

    private final Logger log = LoggerFactory.getLogger(IndexController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String isLogOut = req.getParameter("logOut");
        if (isLogOut != null) {
            session.setAttribute("logIn", false);
            User user = (User)session.getAttribute("user");
            if (user == null) {
                log.warn("session {}: the attribute \"user\" is absent in spit of the attribute \"logIn\" was set to true");
                log.debug("session {}: the user successfully logged out");
            } else {
                log.debug("session {}: user(name: \"{}\", lastName: \"{}\", role: \"{}\") successfully logged out", session.getId(), user.getName(), user.getLastName(), user.getRole().getName());
            }
        }

        final String locale = req.getParameter("locale");
        if (locale != null) {
            session.setAttribute("locale", locale);
            log.debug("session {}: locale has changed to \"{}\"",session.getId(),locale);
        }

        Boolean isLogIn = (Boolean) session.getAttribute("logIn");
        if (isLogIn)
            resp.sendRedirect("./home");
        else {
            req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
        }
    }
}
