package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Connection connection = null;

    public Util() {

    }

    public static Connection getConnection() {
        try {
            if (connection == null) {
                Driver driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);

                Class.forName("com.mysql.cj.jdbc.Driver");
                String USER = "root";
                String URL = "jdbc:mysql://localhost:3306/users";
                String PASSWORD = "wJ687Eb3h6Q9g4";
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Connection lost! " + e);
        }
        return connection;
    }
}
