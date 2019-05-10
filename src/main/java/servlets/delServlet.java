package servlets;

import dao.UserDb;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import models.User;

@WebServlet(value = "/delServlet")
public class delServlet extends HttpServlet {

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        UserDb.delete(login);
        List<User> users = UserDb.select();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/main.jsp").forward(req, resp);
    }
}
