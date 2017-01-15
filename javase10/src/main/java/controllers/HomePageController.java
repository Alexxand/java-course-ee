package controllers;

import model.Permission;
import model.Role;
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
import java.util.Collections;

@WebServlet(value = "/home")
public class HomePageController extends HttpServlet {

    private final Logger log = LoggerFactory.getLogger(HomePageController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        HttpSession session = req.getSession();

        Boolean isLogIn = (Boolean) session.getAttribute("logIn");


        String name = req.getParameter("name");

        String lastName = req.getParameter("lastName");

        User user;
        if (!isLogIn && (name == null || lastName == null)) {
            log.debug("session {}: an unauthorised client tries to access /home page",session.getId());
            resp.sendRedirect(".");
            return;
        }
        if (!isLogIn) {
            name = req.getParameter("name");
            lastName = req.getParameter("lastName");

            Permission permission = new Permission("admin");
            Role role = new Role("admin", Collections.singletonList(permission));
            user = new User(name, lastName, role);

            session.setAttribute("user", user);
            session.setAttribute("logIn", true);

            log.debug("session {}: user(name: \"{}\", lastName: \"{}\", role: \"{}\") successfully logged in",session.getId(),name,lastName,role.getName());
        } else {
            user = (User) session.getAttribute("user");
        }

        req.setAttribute("user", user);
        req.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(req, resp);
    }


}

