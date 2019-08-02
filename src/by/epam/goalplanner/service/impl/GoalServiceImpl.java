package by.epam.goalplanner.service.impl;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.dao.GoalDao;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.service.GoalService;

import java.util.Date;
import java.util.List;

public class GoalServiceImpl implements GoalService {
    private final GoalDao goalDao;

    public GoalServiceImpl(GoalDao goalDao) {
        this.goalDao = goalDao;
    }

    @Override
    public boolean create(String name, String description, Date beginDate, Date endDate, long user_id, long type_id) throws DaoException {
        return goalDao.create(name, description, beginDate, endDate, user_id, type_id);
    }

    @Override
    public List<Goal> findAll() {
        return goalDao.getAll();
    }

    @Override
    public List<Goal> findGoalByDate(Date beginDate, Date endDate) {
        return goalDao.findGoalByDate(beginDate, endDate);
    }

    @Override
    public List<Goal> findGoalByName(String name) {
        return null;
    }

    @Override
    public boolean update(Goal goal) throws DaoException {
        return goalDao.update(goal);
    }

    @Override
    public boolean delete(Goal goal) throws DaoException {
        return goalDao.delete(goal);
    }
}
