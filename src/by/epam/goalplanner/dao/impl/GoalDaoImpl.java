package by.epam.goalplanner.dao.impl;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.beans.builder.Builder;
import by.epam.goalplanner.constant.SqlConstant;
import by.epam.goalplanner.constant.VariableConstant;
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
    public boolean delete(Goal goal) throws DaoException { return executeUpdate(VariableConstant.DELETE_GOAL.getName(), goal.getId()); }

    @Override
    public Goal read(long id) throws DaoException {
        String sqlSuffix = String.format(SqlConstant.WHERE_ID.getName(), id);
        List<Goal> all = getAll(sqlSuffix);
        return all.size() > 0 ? all.get(0) : null;
    }

    @Override
    public List<Goal> getAll() throws DaoException {
        return executeQuery(SqlConstant.GET_ALL_GOALS.getName());
    }

    @Override
    public List<Goal> getAll(String sql) throws DaoException {
        return executeQuery(SqlConstant.GET_ALL_GOALS_WHERE.getName(), sql);
    }

    @Override
    public List<Goal> findGoalByDate(Date beginDate, Date endDate) throws DaoException { return executeQuery(SqlConstant.GET_GOALS_BY_DATE.getName(), beginDate, endDate); }

    @Override
    public List<Goal> findGoalByName(String name) throws DaoException { return executeQuery(SqlConstant.GET_GOALS_BY_NAME.getName(), name); }
}
