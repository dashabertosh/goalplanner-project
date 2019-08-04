package by.epam.goalplanner.dao.impl;


import by.epam.goalplanner.beans.Task;
import by.epam.goalplanner.beans.builder.Builder;
import by.epam.goalplanner.constant.SqlConstant;
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
    public boolean delete(Task task) throws DaoException {
        return executeUpdate(SqlConstant.DELETE_TASK.getName(), task.getId());
    }

    @Override
    public Task read(long id) throws DaoException {
        String sqlSuffix = String.format(SqlConstant.WHERE_ID.getName(), id);
        List<Task> all = getAll(sqlSuffix);
        return all.size() > 0 ? all.get(0) : null;
    }

    @Override
    public List<Task> getAll(String sql) throws DaoException {
        return executeQuery(SqlConstant.SELECT_ALL_TASK_WHERE.getName(), sql);
    }

    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public List<Task> findTasksByDate(long date) throws DaoException {
        return executeQuery(SqlConstant.GET_TASKS_BY_DATE.getName(), date);
    }
}
