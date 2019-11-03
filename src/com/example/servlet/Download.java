package com.example.servlet;

import com.example.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(
        urlPatterns = {
                "/Download"
        }
)
public class Download extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var file = new File(getServletContext().getRealPath("classroom.html"));
        if(file.exists()) {
            resp.reset();
            resp.setContentType("text/html");
            resp.addHeader("Content-Disposition", "attachment; file=\"" + URLEncoder.encode(file.getName(), "utf-8") + "\"");
            resp.setContentLength((int)file.length());
            if(file.length() > 0) {
                String value = Helper.readAll(file);
                resp.getWriter().println(value);

                var out = resp.getOutputStream();
                var in = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int readLength = 0;
                while(((readLength = in.read(buffer)) != -1)) {
                    out.write(buffer, 0, readLength);
                }
                in.close();
                out.close();

            } else {
                resp.getWriter().println("File Error");
            }
        }
    }


}
