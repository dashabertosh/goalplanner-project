package by.epam.goalplanner.dao.impl;


import by.epam.goalplanner.beans.Task;
import by.epam.goalplanner.dao.builder.Builder;
import by.epam.goalplanner.dao.SqlConstant;
import by.epam.goalplanner.dao.TaskDao;
import by.epam.goalplanner.exception.DaoException;

import java.util.Date;
import java.util.List;

public class TaskDaoImpl extends AbstractDao<Task> implements TaskDao {
    public TaskDaoImpl(Builder<Task> builder) {
        super(builder);
    }

    @Override
    public boolean create(String name, String description, Date date, long goalId) throws DaoException {
        return executeUpdate(SqlConstant.CREATE_TASK.getName(), name, description, date, goalId);
    }

    @Override
    public boolean update(Task task) throws DaoException {
        return executeUpdate(SqlConstant.UPDATE_TASK.getName(), task.getName(), task.getDescription(), task.getDate(), task.getGoalId());
    }

    @Override
    public boolean delete(long id) throws DaoException {
        return executeUpdate(SqlConstant.DELETE_TASK.getName(), id);
    }

    @Override
    public boolean deleteWithGoal(long goaId) throws DaoException {
        return executeUpdate(SqlConstant.DELETE_TASK_WITH_GOAL.getName(), goaId);
    }

    @Override
    public boolean deleteWithGoalUser(long goalId) throws DaoException {
        return executeUpdate(SqlConstant.DELETE_WITH_USER_GOAL.getName(), goalId);
    }

    @Override
    public List<Task> findAll(String sql) throws DaoException {
        return executeQuery(SqlConstant.SELECT_ALL_TASK_WHERE.getName(), sql);
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public List<Task> findTasksById(long id) throws DaoException {
        return executeQuery(SqlConstant.GET_TASK_BY_ID.getName(), id);
    }
}
