package by.epam.goalplanner.dao;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.exception.DaoException;

import java.util.List;

public interface UserDao extends BaseDao<User> {
    List<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    boolean create(String login, String password, String name) throws DaoException;
}
