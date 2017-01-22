package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import db.DAO;
import model.Gun;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

@Singleton
public class ImageController extends HttpServlet {
    private DAO dao;

    @Inject
    public ImageController(DAO dao){
        this.dao = dao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam != null) {
            Gun gun = dao.getById(Integer.parseInt(idParam));
            InputStream stream = gun.getImage();
            byte[] buffer = new byte[500];
            OutputStream out = resp.getOutputStream();

            while (stream.read(buffer) != -1){
                out.write(buffer);
            }
        }
    }
}
