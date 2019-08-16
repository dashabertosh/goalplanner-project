package by.epam.goalplanner.dao.impl;

import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.command.Hasher;
import by.epam.goalplanner.dao.builder.Builder;
import by.epam.goalplanner.dao.SqlConstant;
import by.epam.goalplanner.dao.UserDao;
import by.epam.goalplanner.exception.CommandException;
import by.epam.goalplanner.exception.DaoException;

import java.util.List;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    public UserDaoImpl(Builder<User> builder) {
        super(builder);
    }

    @Override
    public boolean create(String login, String password, String name) throws DaoException {
        return executeUpdate(SqlConstant.CREATE_USER.getName(), login, password, name, 1.);
    }
    @Override
    public boolean delete(long id) throws DaoException {
        return executeUpdate(SqlConstant.DELETE_USER.getName(), id);
    }

    @Override
    public boolean deleteWithUser(long id) throws DaoException {
        return executeUpdate(SqlConstant.DELETE_WITH_USER.getName(), id);
    }

    @Override
    public boolean update(User user) throws DaoException {
        return executeUpdate(SqlConstant.UPDATE_USER.getName(),
                user.getLogin(),
                user.getPassword(),
                user.getName(),
                user.getRoleId(),
                user.getId());
    }

    @Override
    public List<User> findAll(String sql) throws DaoException {
        return executeQuery(SqlConstant.SELECT_ALL_USERS_WHERE.getName(), sql);
    }

    @Override
    public List<User> findAll() throws DaoException {
        return executeQuery(SqlConstant.SELECT_ALL_USERS.getName());
    }

    @Override
    public List<User> login(String login, String password) throws DaoException {
        try {
            String hashPassword = Hasher.getHash(password);
            return executeQuery(SqlConstant.SELECT_USER_BY_LOGIN_AND_PASSWORD.getName(), login, hashPassword);
        } catch (CommandException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeQuery(SqlConstant.SELECT_USER_BY_LOGIN_AND_PASSWORD.getName(), login, password);
    }
}
