package by.epam.goalplanner.service.impl;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.dao.GoalDao;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.GoalService;

import java.util.Date;
import java.util.List;

public class GoalServiceImpl implements GoalService {
    private final GoalDao goalDao;

    public GoalServiceImpl(GoalDao goalDao) {
        this.goalDao = goalDao;
    }

    @Override
    public boolean create(String name, String description, Date beginDate, Date endDate, long userId, long typeId) throws ServiceException {
        try {
            return goalDao.create(name, description, beginDate, endDate, userId, typeId); //TODO HUI
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
    public List<Goal> findGoalByDate(Date beginDate, Date endDate) throws ServiceException {
        try {
            return goalDao.findGoalByDate(beginDate, endDate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Goal> findGoalByName(String name) {
        return null;
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
    public boolean delete(Goal goal) throws ServiceException {
        try {
            return goalDao.delete(goal);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
