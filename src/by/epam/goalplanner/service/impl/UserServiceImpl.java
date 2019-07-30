package by.epam.goalplanner.service.impl;


import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.dao.UserDao;
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
    public List<User> findAll() {
        return userDao.getAll(" ");
    }

    @Override
    public Optional<User> login(String login, String password) {
        return userDao.findUserByLoginAndPassword(login, password);
    }

    @Override
    public boolean create(String login, String password, String name) {
        return userDao.create(login, password, name);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        return Optional.empty();
    }
}
