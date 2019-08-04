package by.epam.goalplanner.service.impl;

import by.epam.goalplanner.beans.Task;
import by.epam.goalplanner.dao.TaskDao;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.TaskService;

import java.util.Date;
import java.util.List;

public class TaskServiceImpl implements TaskService {
    private final TaskDao taskDao;

    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public List<Task> findAll() throws ServiceException {
        try {
            return taskDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(Task task) throws ServiceException {
        try {
            return taskDao.delete(task);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Task> findAll(String string) throws ServiceException {
        try {
            return taskDao.getAll(string);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(String name, String description, Date date, long goalId) {
        return false;
    }
}
