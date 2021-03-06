package by.epam.goalplanner.dao.builder;

import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.dao.DbConstant;
import by.epam.goalplanner.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeBuilder implements Builder<Type> {

    public Type build(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(DbConstant.ID.getName());
            String name = resultSet.getString(DbConstant.NAME.getName());
            return new Type(id, name);
        } catch (SQLException e) {
            throw new DaoException("Entity assembly failed", e);
        }
    }


}
