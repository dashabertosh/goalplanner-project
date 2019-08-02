package by.epam.goalplanner.service;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.exception.DaoException;

import java.util.Date;
import java.util.List;

public interface GoalService {
    List<Goal> findGoalByDate(Date beginDate, Date endDate);

    List<Goal> findAll();

    boolean update(Goal goal) throws DaoException;

    boolean delete(Goal goal) throws DaoException;

    boolean create(String name, String description, Date beginDate, Date endDate, long user_id, long type_id) throws DaoException;

    List<Goal> findGoalByName(String name);
}

