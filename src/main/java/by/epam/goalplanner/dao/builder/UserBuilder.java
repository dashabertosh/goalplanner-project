package by.epam.goalplanner.dao.builder;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.dao.DbConstant;
import by.epam.goalplanner.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {
    @Override
    public User build(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(DbConstant.ID.getName());
            String login = resultSet.getString(DbConstant.LOGIN.getName());
            String password = resultSet.getString(DbConstant.PASSWORD.getName());
            String name = resultSet.getString(DbConstant.NAME.getName());
            long roleId = resultSet.getLong(DbConstant.ROLE_ID.getName());
            return new User(id, login, password, name, roleId);
        } catch (SQLException e) {
            throw new DaoException("Entity assembly failed", e);
        }
    }
}
