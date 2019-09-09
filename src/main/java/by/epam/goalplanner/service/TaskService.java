package by.epam.goalplanner.service;

import by.epam.goalplanner.beans.Task;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface TaskService{
    List<Task> findAll() throws ServiceException;

    boolean delete(long id) throws ServiceException;

    boolean deleteWithGoal(long goalId) throws ServiceException;

    List<Task> findAll(String string) throws ServiceException;

    Task findTasksById(long id) throws ServiceException;

    boolean create(String name, String description, Date date, long goalId) throws ServiceException;
}
