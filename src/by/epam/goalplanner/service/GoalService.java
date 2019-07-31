package by.epam.goalplanner.service;

import by.epam.goalplanner.beans.Goal;

import java.util.Date;
import java.util.List;

public interface GoalService extends Service<Goal> {
    List<Goal> findGoalByDate(Date beginDate, Date endDate);

    boolean update(Goal goal);

    boolean delete(Goal goal);

    boolean create(String name, String description, Date beginDate, Date endDate, long user_id, long type_id);

    List<Goal> findGoalByName(String name);
}

