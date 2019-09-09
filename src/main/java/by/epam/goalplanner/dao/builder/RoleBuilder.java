package by.epam.goalplanner.dao.builder;

import by.epam.goalplanner.beans.Role;
import by.epam.goalplanner.dao.DbConstant;
import by.epam.goalplanner.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleBuilder implements Builder<Role> {
    @Override
    public Role build(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(DbConstant.ID.getName());
            String name = resultSet.getString(DbConstant.NAME.getName());
            return new Role(id, name);
        } catch (SQLException e) {
            throw new DaoException("Entity assembly failed", e);
        }
    }
}
