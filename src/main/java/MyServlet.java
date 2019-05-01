import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(value = "/login")
public class MyServlet extends HttpServlet {
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
            getServletContext().getRequestDispatcher("/main.jsp").forward(req, resp);
            //registration(req, resp,"/main.jsp");
        } else if (name == null) {
            registration(req, resp, "/registration.jsp");
        } else {
            if (newUser != null && !getUser(login, password).equals(newUser)) {
                registration(req, resp, "/registration.jsp");
            } else {
                if (newUser == null) {
                    newUser = new User(users.size() + 1, name, login, password);
                } else {
                    newUser.setId(users.size() + 1);
                    newUser.setName(name);
                    newUser.setLogin(login);
                    newUser.setPassword(password);
                }
                UserDb.insert(newUser);
                users = UserDb.select();
                req.setAttribute("name", name);
                req.setAttribute("users", users);
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
