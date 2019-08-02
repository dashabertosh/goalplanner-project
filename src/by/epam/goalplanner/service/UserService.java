package by.epam.goalplanner.service;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(long id);

    Optional<User> login(String login, String password);

    boolean create(String login, String password, String name) throws DaoException;

    Optional<User> findUserByLoginAndPassword(String login, String password);
}
