package org.elsys.ip.servlet.operations;

import org.elsys.ip.servlet.model.User;
import org.elsys.ip.servlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeletingUserServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        deleteUserFromList(req, resp);
        resp.sendRedirect("/admin");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        deleteUserFromList(req, resp);
        resp.sendRedirect("/admin");
    }

    private void deleteUserFromList(HttpServletRequest request, HttpServletResponse response) {
        userService.deleteUser(request.getParameter("name"));
    }
}
