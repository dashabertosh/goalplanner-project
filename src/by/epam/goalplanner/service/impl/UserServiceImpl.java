package by.epam.goalplanner.service.impl;


import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.dao.UserDao;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> login(String login, String password) throws ServiceException {
        try {
            return userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(String login, String password, String name) throws ServiceException {
        try {
            Optional<User> currentUser = userDao.findUserByLoginAndPassword(login, password);
            if (!currentUser.isPresent()) {
                return userDao.create(login, password, name);
            } else {
                return false; // FIXME: 12.08.2019 
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        return Optional.empty();
    }
}
