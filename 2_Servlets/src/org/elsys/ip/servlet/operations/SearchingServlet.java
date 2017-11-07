package org.elsys.ip.servlet.operations;

import org.elsys.ip.servlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchingServlet extends HttpServlet {
    public UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        getServletContext().getRequestDispatcher("/WEB-INF/search.jsp")
                           .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("searchedName");

        if (userService.getByName(name) != null) {
            resp.sendRedirect("/user?name=" + name);
        } else {
            throw new ServletException("The user you were looking for was NOT FOUND");
        }
    }
}
