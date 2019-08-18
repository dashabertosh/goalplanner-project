package by.epam.goalplanner.dao.impl;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.dao.builder.Builder;
import by.epam.goalplanner.dao.SqlConstant;
import by.epam.goalplanner.command.VariableConstant;
import by.epam.goalplanner.dao.GoalDao;
import by.epam.goalplanner.exception.DaoException;

import java.util.Date;
import java.util.List;

public class GoalDaoImpl extends AbstractDao<Goal> implements GoalDao {
    public GoalDaoImpl(Builder<Goal> builder) {
        super(builder);
    }

    @Override
    public boolean create(String name, String description, Date beginDate, Date endDate, long userId, long typeId) throws DaoException {
        return executeUpdate(SqlConstant.CREATE_GOAL.getName(), name, description, beginDate, endDate, userId, typeId);
    }

    @Override
    public boolean update(Goal goal) throws DaoException {
        return executeUpdate(SqlConstant.UPDATE_GOAL.getName(),
                goal.getName(),
                goal.getDescription(),
                goal.getBeginDate(),
                goal.getEndDate(),
                goal.getUserId(),
                goal.getTypeId(),
                goal.getId());
    }

    @Override
    public boolean delete(long id) throws DaoException {
        return executeUpdate(SqlConstant.DELETE_GOAL.getName(), id);
    }

    @Override
    public boolean deleteWithUser(long id) throws DaoException {
        return executeUpdate(SqlConstant.DELETE_WITH_USER.getName(), id);
    }

    @Override
    public boolean deleteWithType(long id) throws DaoException {
        return executeUpdate(SqlConstant.DELETE_WITH_TYPE.getName(), id);
    }

    @Override
    public boolean deleteWithGoal(long id) throws DaoException {
        return executeUpdate(SqlConstant.DELETE_TASK_WITH_GOAL.getName(), id);
    }

    @Override
    public List<Goal> findGoalById(long id) throws DaoException {
        return executeQuery(SqlConstant.SELECT_GOALS_BY_ID.getName(), id);
    }

    @Override
    public List<Goal> findAll() throws DaoException {
        return executeQuery(SqlConstant.GET_ALL_GOALS.getName());
    }

    @Override
    public List<Goal> findAll(String sql) throws DaoException {
        return executeQuery(SqlConstant.GET_ALL_GOALS_WHERE.getName(), sql);
    }

    @Override
    public List<Goal> findGoalByDate(Date beginDate, Date endDate) throws DaoException { return executeQuery(SqlConstant.GET_GOALS_BY_DATE.getName(), beginDate, endDate); }

    @Override
    public List<Goal> findGoalByName(String name) throws DaoException { return executeQuery(SqlConstant.GET_GOALS_BY_NAME.getName(), name); }
}
