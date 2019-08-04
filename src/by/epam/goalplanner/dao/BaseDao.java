package by.epam.goalplanner.dao;

import by.epam.goalplanner.exception.DaoException;

import java.util.List;

public interface BaseDao<Type> {
    boolean update(Type type) throws DaoException;

    boolean delete(Type type) throws DaoException;

    Type read(long id) throws DaoException;

    List<Type> getAll(String sql) throws DaoException;

    List<Type> getAll() throws DaoException;
}
