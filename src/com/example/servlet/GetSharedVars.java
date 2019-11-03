package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        urlPatterns = {"/GetSharedVars"}
)
public class GetSharedVars extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title> Get Shared Vars </title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("Context: " + getServletContext().getAttribute("Context") + "</br>");
        writer.println("HttpSession: " + req.getSession().getAttribute("Session") + "</br>");
        writer.println("HttpRequest: " + req.getAttribute("HttpRequest") + "</br>");
        writer.println("</body>");
        writer.println("</html>");
        writer.flush();
        writer.close();
    }
}
