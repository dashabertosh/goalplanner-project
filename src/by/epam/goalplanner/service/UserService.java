package by.epam.goalplanner.service;

import by.epam.goalplanner.beans.Role;
import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.exception.ServiceException;

import java.util.List;

public interface UserService {
    boolean update(User user) throws ServiceException;

    boolean delete(long id) throws ServiceException;

    List<User> findAll() throws ServiceException;

    List<Role> findAllRoles() throws ServiceException;

    List<User> login(String login, String password) throws ServiceException;

    boolean create(String login, String password, String name) throws ServiceException;

    List<User> findUserByLoginAndPassword(String login, String password) throws ServiceException;
}
