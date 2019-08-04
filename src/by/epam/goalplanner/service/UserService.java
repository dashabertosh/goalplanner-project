package by.epam.goalplanner.service;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll() throws ServiceException;

    Optional<User> findById(long id);

    Optional<User> login(String login, String password) throws ServiceException;

    boolean create(String login, String password, String name) throws ServiceException;

    Optional<User> findUserByLoginAndPassword(String login, String password);
}
