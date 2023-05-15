package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtilDafina {

    private static Connection connection;
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/projekti_knk";
            String user = "root";
            String password = "";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);

            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();

            }

        }
        return connection;
    }
}
