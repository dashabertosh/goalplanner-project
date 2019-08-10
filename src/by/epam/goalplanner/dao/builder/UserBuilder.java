package by.epam.goalplanner.dao.builder;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.dao.DBConstant;
import by.epam.goalplanner.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {
    @Override
    public User build(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(DBConstant.ID.getName());
            String login = resultSet.getString(DBConstant.LOGIN.getName());
            String password = resultSet.getString(DBConstant.PASSWORD.getName());
            String name = resultSet.getString(DBConstant.NAME.getName());
            long roleId = resultSet.getLong(DBConstant.ROLE_ID.getName());
            return new User(id, login, password, name, roleId);
        } catch (SQLException e) {
            throw new DaoException("Entity assembly failed", e);
        }
    }
}
