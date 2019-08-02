package by.epam.goalplanner.dao;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    List<User> findUserByName(String name);

    Optional<User> findUserByLoginAndPassword(String login, String password);

    boolean create(String login, String password, String name) throws DaoException;
}
