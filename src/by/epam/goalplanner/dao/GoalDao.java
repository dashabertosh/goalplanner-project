package by.epam.goalplanner.dao;

import by.epam.goalplanner.beans.Goal;

import java.util.List;

public interface GoalDao extends BaseDao<Goal> {
    List<Goal> findGoalByDate(long beginDate, long endDate);

    boolean create(String name, String description, long beginDate, long endDate, long userId, long typeId);
}
