package by.epam.goalplanner.dao;

import by.epam.goalplanner.exception.DaoException;

import java.util.List;

public interface BaseDao<Type> {
    boolean delete(long id) throws DaoException;

    boolean update(Type type) throws DaoException;

    List<Type> findAll(String sql) throws DaoException;

    List<Type> findAll() throws DaoException;
}
