package by.epam.goalplanner.dao.builder;

import by.epam.goalplanner.exception.DaoException;

import java.sql.ResultSet;

public interface Builder<T> {
    T build(ResultSet resultSet) throws DaoException;
}
