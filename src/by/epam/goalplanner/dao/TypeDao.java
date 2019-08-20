package by.epam.goalplanner.dao;

import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.exception.DaoException;

import java.util.List;

public interface TypeDao extends BaseDao<Type> {
    boolean create(String name) throws DaoException;

    long findIdByName(String name) throws DaoException;

    List<Type> findSomeTypes() throws DaoException;

    List<Type> findIdTypeById(long id) throws DaoException;
}
