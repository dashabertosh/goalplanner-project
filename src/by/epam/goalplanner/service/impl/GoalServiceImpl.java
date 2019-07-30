package by.epam.goalplanner.service.impl;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.dao.GoalDao;
import by.epam.goalplanner.service.GoalService;

import java.util.List;

public class GoalServiceImpl implements GoalService {
    private final GoalDao goalDao;

    public GoalServiceImpl(GoalDao goalDao) {
        this.goalDao = goalDao;
    }

    @Override
    public List<Goal> findAll() {
        return goalDao.getAll("");
    }

    @Override
    public List<Goal> findGoalByDate(long beginDate, long endDate) {
        return goalDao.findGoalByDate(beginDate, endDate);
    }

    @Override
    public boolean update(Goal goal) {
        return goalDao.update(goal);
    }

    @Override
    public boolean delete(Goal goal) {
        return goalDao.delete(goal);
    }
}
