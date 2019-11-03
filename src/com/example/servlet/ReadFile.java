package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@WebServlet(
        urlPatterns = {"/ReadFile"}
)
public class ReadFile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = getServletContext().getRealPath("readfile.html");
        File file = new File(path);
        if(file.exists()) {
            var reader = new BufferedReader(new FileReader(file));
            String line = null;
            var writer = resp.getWriter();
            writer.println("<xmp>");
            while((line = reader.readLine()) != null) {
                writer.println(line);
            }
            writer.println("</xmp>");
        } else {
            var writer = resp.getWriter();
            writer.println("File Not Found");
        }
    }
}
