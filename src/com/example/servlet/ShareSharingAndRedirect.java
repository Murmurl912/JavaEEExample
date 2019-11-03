package com.example.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        urlPatterns = {
                "/ShareAndRedirect"
        }
)
public class ShareSharingAndRedirect extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // store vars in ServletContext
        ServletContext context = this.getServletContext();
        context.setAttribute("Context", "data stored in context");

        // store vars in HttpSession
        HttpSession session = req.getSession();
        session.setAttribute("Session", "data stored in session");

        // store vars in request
        req.setAttribute("HttpRequest", "data stored in request");

        resp.sendRedirect("/JavaEEExample/GetSharedVars");
    }
}
