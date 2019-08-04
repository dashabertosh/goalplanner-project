package by.epam.goalplanner.beans.builder;

import by.epam.goalplanner.beans.Task;
import by.epam.goalplanner.constant.VariableConstant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TaskBuilder implements Builder<Task> {
    @Override
    public Task build(ResultSet resultSet) {
        try {
            long id = resultSet.getLong(VariableConstant.ID.getName());
            String name = resultSet.getString(VariableConstant.NAME.getName());
            String description = resultSet.getString(VariableConstant.DESCRIPTION.getName());
            Date date = resultSet.getDate(VariableConstant.DATE.getName());
            long goal_id = resultSet.getLong(VariableConstant.GOAL_ID.getName());
            return new Task(id, name, description, date, goal_id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
