package by.epam.goalplanner.dao.impl;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.beans.builder.Builder;
import by.epam.goalplanner.constant.SqlConstant;
import by.epam.goalplanner.dao.UserDao;
import by.epam.goalplanner.exception.DaoException;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    public UserDaoImpl(Builder<User> builder) {
        super(builder);
    }

    @Override
    public boolean create(String login, String password, String name) throws DaoException {
        return executeUpdate(SqlConstant.CREATE_USER.getName(), login, password, name, 1.);
    }
    @Override
    public boolean delete(User user) throws DaoException {
        return executeUpdate(SqlConstant.DELETE_USER.getName(), user.getId());
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
    public User read(long id) throws DaoException {
        String sqlSuffix = String.format(SqlConstant.WHERE_ID.getName(), id);
        List<User> all = getAll(sqlSuffix);
        return all.size() > 0 ? all.get(0) : null;
    }

    @Override
    public List<User> getAll(String sql) throws DaoException {
        return executeQuery(SqlConstant.SELECT_ALL_USERS_WHERE.getName(), sql);
    }

    @Override
    public List<User> getAll() throws DaoException {
        return executeQuery(SqlConstant.SELECT_ALL_USERS.getName());
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeQueryForSingleResult(SqlConstant.SELECT_USER_BY_LOGIN_AND_PASSWORD.getName(), login, password);
    }

    @Override
    public List<User> findUserByName(String name) throws DaoException {
        return executeQuery(SqlConstant.FIND_USER_BY_NAME.getName(), name);
    }
}
