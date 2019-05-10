package dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.User;
import org.apache.log4j.Logger;

public class UserDb {
    private static final Logger logger = Logger.getLogger(UserDb.class);

    public static ArrayList<User> select() {
        ArrayList<User> users = new ArrayList<>();
        Connection connection = DbConnector.connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                String role = resultSet.getString(5);
                User user = new User(id, name, login, password, role);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("Can't return users; ", e);
        }
        return users;
    }

    public static User selectOne(String login, String param) {
        User user = null;
        Connection connection = DbConnector.connect();
        try {
            String sql = "SELECT * FROM users WHERE " + param + "=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.debug(sql);
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String log = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                String role = resultSet.getString(5);
                user = new User(id, name, log, password, role);
            }
        } catch (SQLException e) {
            logger.error("Can't return user; ", e);
        }
        return user;
    }

    public static int insert(User user) {
        Connection connection = DbConnector.connect();
        String sql = "INSERT INTO users (login, password, name, role) Values (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getRole());
            logger.debug(sql);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't insert user;", e);
        }
        return 0;
    }

    public static int update(User user) {
        Connection connection = DbConnector.connect();
        String sql = "UPDATE users SET name = ?, login = ?, password = ?, role = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setInt(5, user.getId());
            logger.debug(sql);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Can't update user;", e);
        }
        return 0;
    }

    public static boolean delete(String login) {
        Connection connection = DbConnector.connect();
        String sql = "DELETE FROM users WHERE login = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.execute();
            logger.debug(sql);
            return true;
        } catch (SQLException e) {
            logger.error("Can't delete user;", e);
        }
        return false;
    }
}