package by.epam.goalplanner.service.impl;

import by.epam.goalplanner.beans.Role;
import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.dao.GoalDao;
import by.epam.goalplanner.dao.RoleDao;
import by.epam.goalplanner.dao.TaskDao;
import by.epam.goalplanner.dao.UserDao;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final GoalDao goalDao;
    private final TaskDao taskDao;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, GoalDao goalDao, TaskDao taskDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.goalDao = goalDao;
        this.taskDao = taskDao;
    }

    @Override
    public boolean update(User user) throws ServiceException {
        try {
            return userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        try {
            taskDao.deleteWithGoalUser(id);
            goalDao.deleteWithUser(id);
            return userDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Role> findAllRoles() throws ServiceException {
        try {
            return roleDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> login(String login, String password) throws ServiceException {
        try {
            return userDao.login(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findUserByLoginAndPassword(String login, String password) throws ServiceException {
        try {
            return userDao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(String login, String password, String name) throws ServiceException {
        try {
            return userDao.create(login, password, name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }
}
