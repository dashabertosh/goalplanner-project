package by.epam.goalplanner.service;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface GoalService {
    List<Goal> findGoalByDate(Date beginDate, Date endDate) throws ServiceException;

    List<Goal> findAll() throws ServiceException;

    List<Goal> findAll(String string) throws ServiceException;

    boolean update(Goal goal) throws ServiceException;

    boolean delete(long id) throws ServiceException;

    boolean deleteWithUser(long id) throws ServiceException;

    boolean create(String name, String description, Date beginDate, Date endDate, long userId, long typeId) throws ServiceException;

    Goal findGoalByName(String name) throws ServiceException;

    Goal findGoalById(long id) throws ServiceException;
}

