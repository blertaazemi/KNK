package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static Connection connection;

    //klasa ndihmese qe mundeson lidhjen me bazen e te dhenave

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/projekti_knk";
            String user = "root";
            String password = "bbddd23";
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }
}
