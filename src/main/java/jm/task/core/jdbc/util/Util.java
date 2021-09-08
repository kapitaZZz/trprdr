package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static Connection connection = null;
    private static SessionFactory sessionFactory = null;

    public Util() {

    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();

                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/users");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "wJ687Eb3h6Q9g4");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "create");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

//                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .applySetting(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory();


            } catch (Exception e) {
                System.out.println("Connection HIBERNATE lost" + e);
            }
        }
        return sessionFactory;
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
