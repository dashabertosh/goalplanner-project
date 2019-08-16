package by.epam.goalplanner.dao;

import by.epam.goalplanner.beans.Task;
import by.epam.goalplanner.exception.DaoException;

import java.util.Date;
import java.util.List;

public interface TaskDao extends BaseDao<Task> {
    List<Task> findTasksById(long id) throws DaoException;

    boolean create(String name, String description, Date date, long goalId) throws DaoException;

    boolean deleteWithGoal(long goalId) throws DaoException;

    boolean deleteWithGoalUser(long goalId) throws DaoException;
}
