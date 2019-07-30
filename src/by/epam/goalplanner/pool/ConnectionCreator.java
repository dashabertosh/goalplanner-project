package by.epam.goalplanner.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public enum  ConnectionCreator {
    INSTANCE;

    ConnectionCreator() {
    }

    private static ResourceBundle resource = ResourceBundle.getBundle("db");
    private static String url = resource.getString("db.url");
    private static String user = resource.getString("db.user");
    private static String password = resource.getString("db.password");

    Connection createConnection() throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        return DriverManager.getConnection(url, user, password);
    }
}
