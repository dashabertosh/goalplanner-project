package by.epam.goalplanner.dao;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.exception.DaoException;

import java.util.Date;
import java.util.List;

public interface GoalDao extends BaseDao<Goal> {
    List<Goal> findGoalByDate(Date beginDate, Date endDate);

    List<Goal> findGoalByName(String name);

    boolean create(String name, String description, Date beginDate, Date endDate, long userId, long typeId) throws DaoException;
}
