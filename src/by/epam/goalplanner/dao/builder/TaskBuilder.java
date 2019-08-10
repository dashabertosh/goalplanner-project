package by.epam.goalplanner.dao.builder;

import by.epam.goalplanner.beans.Task;
import by.epam.goalplanner.dao.DBConstant;
import by.epam.goalplanner.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TaskBuilder implements Builder<Task> {
    @Override
    public Task build(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(DBConstant.ID.getName());
            String name = resultSet.getString(DBConstant.NAME.getName());
            String description = resultSet.getString(DBConstant.DESCRIPTION.getName());
            Date date = resultSet.getDate(DBConstant.DATE.getName());
            long goal_id = resultSet.getLong(DBConstant.GOAL_ID.getName());
            return new Task(id, name, description, date, goal_id);
        } catch (SQLException e) {
            throw new DaoException("Entity assembly failed", e);
        }
    }
}
