package by.epam.goalplanner.service;

import by.epam.goalplanner.beans.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends Service<User>{
    Optional<User> findById(long id);

    Optional<User> login(String login, String password);

    boolean create(String login, String password, String name);

    Optional<User> findUserByLoginAndPassword(String login, String password);
}
