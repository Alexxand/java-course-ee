package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import db.DAO;
import model.Gun;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

@Singleton
public class AddController extends HttpServlet {
    private DAO dao;

    @Inject
    public AddController(DAO dao){
        this.dao = dao;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(ServletFileUpload.isMultipartContent(req)){
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
            String name = null;
            double caliber = 0;
            double rate = 0;
            InputStream imageStream = null;
            long imageSize = 0;
            boolean success;
            try {
                List<FileItem> items = upload.parseRequest(req);
                for(FileItem item : items){
                    if(item.isFormField()){
                        switch (item.getFieldName()){
                            case "name":
                                name = item.getString();
                                break;
                            case "caliber":
                                caliber = Double.parseDouble(item.getString());
                                break;
                            case "rate":
                                rate = Double.parseDouble(item.getString());
                                break;
                        }
                    } else {
                        if(item.getFieldName().equals("image")){
                            imageStream = item.getInputStream();
                            imageSize = item.getSize();
                        }
                    }
                }
                success = dao.add(name,caliber,rate,imageStream,imageSize);
            } catch (FileUploadException e){
                success = false;
                e.printStackTrace();
            }
            req.setAttribute("isNew",false);
            req.setAttribute("success",success);
            req.getRequestDispatcher("/WEB-INF/add/index.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isNew",true);
        req.getRequestDispatcher("/WEB-INF/add/index.jsp").forward(req,resp);
    }
}