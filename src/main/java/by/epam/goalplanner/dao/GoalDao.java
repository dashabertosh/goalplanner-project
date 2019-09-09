package by.epam.goalplanner.dao;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.exception.DaoException;

import java.util.Date;
import java.util.List;

public interface GoalDao extends BaseDao<Goal> {
    List<Goal> findGoalByDate(Date beginDate, Date endDate) throws DaoException;

    List<Goal> findGoalByName(String name) throws DaoException;

    List<Goal> findGoalById(long id) throws DaoException;

    boolean create(String name, String description, Date beginDate, Date endDate, long userId, long typeId) throws DaoException;

    boolean deleteWithUser(long id) throws DaoException;

    boolean deleteWithGoal(long id) throws DaoException;

    boolean deleteWithType(long id) throws DaoException;

}
