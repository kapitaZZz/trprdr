package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.util.*;
import jm.task.core.jdbc.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final Connection connection = Util.getConnection();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE users (" +
                    " id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    " name VARCHAR(45) NOT NULL," +
                    " lastName VARCHAR(45) NOT NULL," +
                    " age INT NOT NULL");

        } catch (SQLException e) {
            System.out.println("Table did not create! " + e);
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE users");
        } catch (SQLException e) {
            System.out.println("Table did not delete! " + e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users " +
                "(name, lastName, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, String.valueOf(age));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Could not save new user! " + e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users " +
                "WHERE id = ?")) {
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            System.out.println("Could not remove user! " + e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {

            while (resultSet.next()) {
                User user = new User(resultSet.getString("name")
                        , resultSet.getString("lastName")
                        , resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println("Unable to select any user from table. " + e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE * FROM users");
        } catch (SQLException e) {
            System.out.println("Could not delete any data from table. " + e);
        }
    }

}