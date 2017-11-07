package org.elsys.ip.servlet.operations;

import org.elsys.ip.servlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UpdatingUserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        updateUserInfo(req, resp);
        resp.sendRedirect("/admin");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        updateUserInfo(req, resp);
        resp.sendRedirect("/admin");
    }

    private void updateUserInfo(HttpServletRequest request, HttpServletResponse response) {
        userService.updateUser(request.getParameter("updatedName"),
                               request.getParameter("updatedEmail"),
                               request.getParameter("oldName"));
    }
}
