package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
    private static final String url = "jdbc:mysql://localhost:3306/mysite?"
            + "useUnicode=true"
            + "&useJDBCCompliantTimezoneShift=true"
            + "&useLegacyDatetimeCode=false"
            + "&serverTimezone=UTC";
    private static final String username = "root";
    private static final String password = "root";

    public static Connection connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
