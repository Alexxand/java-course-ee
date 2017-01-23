package controllers;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import db.DAO;
import model.Gun;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static controllers.DeleteController.DeleteControllerResult.*;


@Singleton
public class DeleteController extends HttpServlet {
    private DAO dao;

    @Inject
    public DeleteController(DAO dao) {
        this.dao = dao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        String name = req.getParameter("name");
        String action = req.getParameter("action");

        if (action == null && idParam == null && name == null || action != null && action.equals("cancel"))
            req.setAttribute("result", FIND);
        else if (action == null && idParam != null) {
                int id = Integer.parseInt(idParam);
                Gun gun = dao.getById(id);
                req.setAttribute("result", CONFIRM);
                req.setAttribute("gun", gun);
        } else if (action != null &&  action.equals("confirm")) {
                int id = Integer.parseInt(idParam);
                if (dao.delete(id))
                    req.setAttribute("result", SUCCESS);
                else
                    req.setAttribute("result", FAIL);
        }
        req.getRequestDispatcher("/WEB-INF/delete/index.jsp").forward(req, resp);
    }

    public enum DeleteControllerResult {
        SUCCESS,
        FAIL,
        FIND,
        CONFIRM
    }
}