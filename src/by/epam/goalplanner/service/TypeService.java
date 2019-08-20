package by.epam.goalplanner.service;


import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.exception.ServiceException;

import java.util.List;

public interface TypeService {
    List<Type> findAll() throws ServiceException;

    List<Type> findAll(long id) throws ServiceException;

    boolean create(String name) throws ServiceException;

    boolean delete(long id) throws ServiceException;

    long findIdByName(String name) throws ServiceException;

    List<Type> findSomeTypes() throws ServiceException;

    List<Type> findIdTypeById(long id) throws ServiceException;
}
