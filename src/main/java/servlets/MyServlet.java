package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.Roles;
import models.User;
import dao.UserDb;
import org.apache.log4j.Logger;


@WebServlet(value = "/login")
public class MyServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserDb.class);
    List<User> users = new ArrayList<>();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("nameUser");
        List<User> users = UserDb.select();
        User newUser = UserDb.selectOne(login, "login");
        if (newUser != null && newUser.getName() != null) {
            req.setAttribute("name", newUser.getName());
            req.setAttribute("users", users);
            req.setAttribute("role", newUser.getRole());
            req.getSession().setAttribute("user", newUser);
            logger.info("Autorized in system " + newUser.getLogin());
            getServletContext().getRequestDispatcher("/main.jsp").forward(req, resp);
        } else if (name == null) {
            logger.info("Add new user");
            registration(req, resp, "/registration.jsp");
        } else {
            if (newUser != null && !getUser(login, password).equals(newUser)) {
                logger.info("Wrong login/password");
                registration(req, resp, "/registration.jsp");
            } else {
                if (newUser == null) {
                    newUser = new User(users.size() + 1, name, login, password, Roles.user.toString());
                    logger.debug("Create new User with role - user;");
                } else {
                    newUser.setId(users.size() + 1);
                    newUser.setName(name);
                    newUser.setLogin(login);
                    newUser.setPassword(password);
                    logger.debug("Change info about user;");
                }
                UserDb.insert(newUser);
                users = UserDb.select();
                req.setAttribute("name", name);
                req.setAttribute("users", users);
                req.setAttribute("role", newUser.getRole());
                getServletContext().getRequestDispatcher("/main.jsp").forward(req, resp);
            }
        }
    }

    private void registration(HttpServletRequest req, HttpServletResponse resp, String jspPage) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(jspPage);
        requestDispatcher.include(req, resp);
    }

    private User getUser(String login, String password) {
        User newUser = new User();
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                newUser = user;
            }
        }
        return newUser;
    }
}
