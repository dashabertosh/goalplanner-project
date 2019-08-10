package by.epam.goalplanner.service;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface GoalService {
    List<Goal> findGoalByDate(Date beginDate, Date endDate) throws ServiceException;

    List<Goal> findAll() throws ServiceException;

    boolean update(Goal goal) throws ServiceException;

    boolean delete(Goal goal) throws ServiceException;

    boolean create(String name, String description, Date beginDate, Date endDate, long userId, long typeId) throws ServiceException;

    long findGoalIdByName(String name) throws ServiceException;
}

