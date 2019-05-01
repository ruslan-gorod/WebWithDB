import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDb {
    private static String url = "jdbc:mysql://localhost:3306/mysite?"
            + "useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false"
            + "&serverTimezone=UTC";
    private static String username = "root";
    private static String password = "root";

    public static ArrayList<User> select() {
        ArrayList<User> users = new ArrayList<User>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//.getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String login = resultSet.getString(2);
                    String password = resultSet.getString(3);
                    String name = resultSet.getString(4);
                    User user = new User(id, name, login, password);
                    users.add(user);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return users;
    }

    public static User selectOne(String login, String param) {
        User user = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql = "SELECT * FROM users WHERE " + param + "=?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, login);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        String log = resultSet.getString(2);
                        String password = resultSet.getString(3);
                        String name = resultSet.getString(4);
                        user = new User(id, name, log, password);
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return user;
    }

    public static int insert(User user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {

                String sql = "INSERT INTO users (login, password, name) Values (?, ?, ?)";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, user.getLogin());
                    preparedStatement.setString(2, user.getPassword());
                    preparedStatement.setString(3, user.getName());
                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public static int update(User user) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql = "UPDATE users SET name = ?, login = ?, password = ? WHERE id = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, user.getName());
                    preparedStatement.setString(2, user.getLogin());
                    preparedStatement.setString(3, user.getPassword());
                    preparedStatement.setInt(4, user.getId());
                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public static boolean delete(String login) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql = "DELETE FROM users WHERE login = ?";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, login);
                    preparedStatement.execute();
                    return true;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }
}
