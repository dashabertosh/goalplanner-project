package by.epam.goalplanner.service;


import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.exception.ServiceException;

import java.util.List;

public interface TypeService {
    List<Type> findAll() throws ServiceException;

    boolean create(String name) throws ServiceException;

    long findIdByName(String name) throws ServiceException;

    long findIdTypeById(long id) throws ServiceException;
}
