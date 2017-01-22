package controllers;



import com.google.inject.Inject;
import com.google.inject.Singleton;
import db.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Singleton
public class DeleteController extends HttpServlet {
    private DAO dao;

    @Inject
    public DeleteController(DAO dao){
        this.dao = dao;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/delete/index.jsp").forward(req,resp);
    }
}