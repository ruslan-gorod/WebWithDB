package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.log4j.Logger;

public class DbConnector {
    private static Connection connection;
    private static final Logger logger = Logger.getLogger(UserDb.class);
    private static final String url = "jdbc:mysql://localhost:3306/mysite?"
            + "useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false"
            + "&serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            logger.debug("Connect to DB");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            logger.error("Can't connect to DB;", e);
        }
    }

    public static Connection connect() {
        return connection;
    }
}
