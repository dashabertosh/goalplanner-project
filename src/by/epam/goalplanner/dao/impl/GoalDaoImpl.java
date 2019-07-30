package by.epam.goalplanner.dao.impl;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.beans.builder.Builder;
import by.epam.goalplanner.dao.GoalDao;
import by.epam.goalplanner.pool.ProxyConnection;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GoalDaoImpl extends AbstractDao<Goal> implements GoalDao {

    private static final String GET_ALL_GOALS_WHERE  = "SELECT `id`, `name`, `description`, `begin_date`, `end_date`," +
            " `user_id`, `type_id` FROM `goal` ?";
    private static final String GET_GOALS_BY_DATE  = "SELECT `id`, `name`, `description`, `begin_date`, `end_date`," +
            " `user_id`, `type_id` FROM `goal` WHERE `begin_date` = ? AND `end_date` = ?";
    private static final String CREATE_GOAL = "INSERT INTO `goal` (`name`, `description`, `begin_date`, `end_date`, " +
            "`user_id`, `type_id`) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_GOAL = "UPDATE `goal` SET `name` = '?', `description` = ?, " +
            "`begin_date` = ?, `end_date` = ?, `user_id` = ?, `type_id` = ? WHERE `goal`.`id` = ?";
    private static final String DELETE_GOAL = "DELETE FROM `goal` WHERE `goal`.`id` = ?";

    public GoalDaoImpl(ProxyConnection connection, Builder<Goal> builder) {
        super(connection, builder);
    }

    @Override
    public boolean create(String name, String description, long beginDate, long endDate, long userId, long typeId) {
        return executeUpdate(CREATE_GOAL, name, description, beginDate, endDate, userId, typeId);
    }

    @Override
    public boolean update(Goal goal) {
        return executeUpdate(UPDATE_GOAL, goal.getName(), goal.getDescription(), goal.getBeginDate(), goal.getEndDate(), goal.getUserId(), goal.getType_id(), goal.getId());
    }

    @Override
    public boolean delete(Goal goal) {
        return executeUpdate(DELETE_GOAL, goal.getId());
    }

    @Override
    public Goal read(long id) {
        String sqlSuffix = String.format("WHERE id=%d", id);
        List<Goal> all = getAll(sqlSuffix);
        return all.size() > 0 ? all.get(0) : null;
    }

    @Override
    public List<Goal> getAll(String sql) {
        return executeQuery(GET_ALL_GOALS_WHERE, sql);
    }

    @Override
    public List<Goal> findGoalByDate(long beginDate, long endDate) {
        return executeQuery(GET_GOALS_BY_DATE, beginDate, endDate);
    }
}
