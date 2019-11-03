package com.example.servlet;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        urlPatterns = "/Share"
)
public class ShareSharingVars extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // store vars in ServletContext
        ServletContext context = this.getServletContext();
        context.setAttribute("Context", "data stored in context");

        // store vars in HttpSession
        HttpSession session = request.getSession();
        session.setAttribute("Session", "data stored in session");

        // store vars in request
        request.setAttribute("HttpRequest", "data stored in request");


        // read data show in page
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title> Share Sharing Vars </title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("Context: " + getServletContext().getAttribute("Context") + "</br>");
        writer.println("HttpSession: " + request.getSession().getAttribute("Session") + "</br>");
        writer.println("HttpRequest: " + request.getAttribute("HttpRequest") + "</br>");
        writer.println("<a href=\"/JavaEEExample/GetSharedVars\"> Get Shared Vars </a>");
        writer.println("</body>");
        writer.println("</html>");
        writer.flush();
        writer.close();
    }

}
