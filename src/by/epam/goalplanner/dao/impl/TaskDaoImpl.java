package by.epam.goalplanner.dao.impl;


import by.epam.goalplanner.beans.Task;
import by.epam.goalplanner.beans.builder.Builder;
import by.epam.goalplanner.dao.TaskDao;
import by.epam.goalplanner.pool.ProxyConnection;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class TaskDaoImpl extends AbstractDao<Task> implements TaskDao {

    private static final String SELECT_ALL_TASK_WHERE = "SELECT `id`, `name`, `description`, `date`, `goal_id` FROM `task` ?";
    private static final String GET_TASKS_BY_DATE = "SELECT `id`, `name`, `description`, `date`, `goal_id` FROM `task` WHERE `date` = ?";
    private static final String CREATE_TASK = "INSERT INTO `task` (`name`, `description`, `date`, `goal_id`, `done`) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_TASK = "UPDATE `task` SET `name` = ?, `description` = ?, `date` = ?, `goal_id` = ?, `done` = ? WHERE `task`.`id` = ?";
    private static final String DELETE_TASK = "DELETE FROM `task` WHERE `task`.`id` = ?";

    public TaskDaoImpl(ProxyConnection connection, Builder<Task> builder) {
        super(connection, builder);
    }

    @Override
    public boolean create(String name, String description, Date date, byte done, long goal_id) {
        return executeUpdate(CREATE_TASK, name, description, date, done, goal_id);
    }

    @Override
    public boolean update(Task task) {
        return executeUpdate(UPDATE_TASK, task.getName_task(), task.getDescription_task(), task.getDate(), task.getGoals_ID(), task.isDone());
    }

    @Override
    public boolean delete(Task task) {
        return executeUpdate(DELETE_TASK, task.getId());
    }

    @Override
    public Task read(long id) {
        String sqlSuffix = String.format("WHERE id=%d", id);
        List<Task> all = getAll(sqlSuffix);
        return all.size() > 0 ? all.get(0) : null;
    }

    @Override
    public List<Task> getAll(String sql) {
        return executeQuery(SELECT_ALL_TASK_WHERE, sql);
    }

    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public List<Task> findTasksByDate(long date) {
        return executeQuery(GET_TASKS_BY_DATE, date);
    }
}
