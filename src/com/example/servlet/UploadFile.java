package com.example.servlet;

import com.example.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet(
        urlPatterns = {"/UploadFile"}
)
@MultipartConfig
public class UploadFile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String value = Helper.readAll(new File(getServletContext().getRealPath("fileupload.html")));
        resp.getWriter().println(value);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Part part = req.getPart("file");
        String headerInfo = part.getHeader("content-disposition");
        String fileName = UUID.randomUUID().toString();
        headerInfo = headerInfo.substring(headerInfo.indexOf("filename"));
        headerInfo = headerInfo.substring(
                headerInfo.indexOf("\"") + 1,
                headerInfo.indexOf("\"", headerInfo.indexOf("\"") + 1));
        String[] array = headerInfo.split("[\\\\/]");
        if(array.length > 0){
            fileName += array[array.length - 1];
        }
        String fileSaveFolder = getServletContext().getRealPath("/upload");
        String fileSavePath = fileSaveFolder + File.separator + fileName;
        File f = new File(fileSaveFolder + File.separator);
        if(!f.exists()) {
            f.mkdirs();
        }
        part.write(fileSavePath);
        resp.getWriter().println("File Uploaded");

    }
}
