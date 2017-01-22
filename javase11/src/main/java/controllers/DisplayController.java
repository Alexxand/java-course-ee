package controllers;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import db.DAO;
import model.Gun;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Singleton
public class DisplayController extends HttpServlet {
    private static final int MAX_ELEMS_ON_PAGE = 20;

    private DAO dao;

    @Inject
    public DisplayController(DAO dao){
        this.dao = dao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageParam = req.getParameter("page");
        int page;
        if (pageParam == null)
            page = 1;
        else
            page = Integer.parseInt(pageParam);

        List<Gun> gunList = dao.getRange((page-1)*MAX_ELEMS_ON_PAGE + 1,page*MAX_ELEMS_ON_PAGE);

        long last = (dao.getRawsNumber() - 1) / MAX_ELEMS_ON_PAGE + 1;
        long firstInPaginator = (page >2) ? page - 2 : 1;

        req.setAttribute("gunList",gunList);
        req.setAttribute("last",last);
        req.setAttribute("firstInPaginator",firstInPaginator);

        req.getRequestDispatcher("/WEB-INF/display/index.jsp").forward(req,resp);
    }
}
