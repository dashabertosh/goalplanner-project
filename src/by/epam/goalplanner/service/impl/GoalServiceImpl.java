package by.epam.goalplanner.service.impl;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.dao.GoalDao;
import by.epam.goalplanner.dao.TaskDao;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.GoalService;

import java.util.Date;
import java.util.List;

public class GoalServiceImpl implements GoalService {
    private final GoalDao goalDao;
    private final TaskDao taskDao;

    public GoalServiceImpl(GoalDao goalDao, TaskDao taskDao) {
        this.goalDao = goalDao;
        this.taskDao = taskDao;
    }

    @Override
    public boolean create(String name, String description, Date beginDate, Date endDate, long userId, long typeId) throws ServiceException {
        try {
            return goalDao.create(name, description, beginDate, endDate, userId, typeId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Goal> findAll() throws ServiceException {
        try {
            return goalDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Goal> findAll(String string) throws ServiceException {
        try {
            return goalDao.getAll(string);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Goal> findGoalByDate(Date beginDate, Date endDate) throws ServiceException {
        try {
            return goalDao.findGoalByDate(beginDate, endDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Goal findGoalByName(String name) throws ServiceException {
        try {
            return goalDao.findGoalByName(name).get(0);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Goal findGoalById(long id) throws ServiceException {
        try {
            return goalDao.findGoalById(id).get(0);
        } catch (DaoException e) {
            throw new ServiceException( e);
        }
    }

    @Override
    public boolean update(Goal goal) throws ServiceException {
        try {
            return goalDao.update(goal);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        try {
            taskDao.deleteWithGoal(id);
            return goalDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
