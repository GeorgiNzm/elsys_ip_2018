package org.elsys.ip.servlet.operations;

import org.elsys.ip.servlet.model.User;
import org.elsys.ip.servlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddingUsersServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        getServletContext().getRequestDispatcher("/WEB-INF/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            try {
                // Get static field before new object is initialized, using reflection
                List<Field> fields = Arrays.asList(UserService.class.getDeclaredFields());

                List<Field> staticfields = fields.stream()
                        .filter(f -> Modifier.isStatic(f.getModifiers()))
                        .collect(Collectors.toList());

                staticfields.get(1).setAccessible(true);

                int counter = staticfields.get(1).getInt(null);

                staticfields.get(1).setInt(this, counter + 1);

                userService.addUser(new User(++counter, req.getParameter("name"),
                                                        req.getParameter("email")));

                resp.sendRedirect("/admin");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

    }
}
