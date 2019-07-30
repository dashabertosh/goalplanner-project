package by.epam.goalplanner.service;

import by.epam.goalplanner.beans.Goal;

import java.util.List;

public interface GoalService extends Service<Goal> {
    List<Goal> findGoalByDate(long beginDate, long endDate);

    boolean update(Goal goal);

    boolean delete(Goal goal);
}
