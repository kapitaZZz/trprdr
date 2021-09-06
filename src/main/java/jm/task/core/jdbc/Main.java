package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("John", "Smith", (byte) 50);
        userService.saveUser("Jane", "Smith", (byte) 45);
        userService.saveUser("Steve", "Malkovich", (byte) 30);
        userService.saveUser("Peter", "Pen", (byte) 15);

        userService.getAllUsers();

        userService.removeUserById(2L);

        userService.cleanUsersTable();

        userService.getAllUsers();

        userService.dropUsersTable();

    }
}
