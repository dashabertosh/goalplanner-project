package by.epam.goalplanner.service;


import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.exception.DaoException;

import java.util.List;

public interface TypeService {
    List<Type> findAll();

    boolean create(String name) throws DaoException;

    long findIdByName(String name);
}
