package by.epam.goalplanner.beans.builder;

import by.epam.goalplanner.beans.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TaskBilder implements Builder<Task> {
    private static final String ID_TASK = "id";
    private static final String NAME_TASK = "name";
    private static final String DESCRIPTION = "description";
    private static final String DATE = "date";
    private static final String IS_DONE = "is_Done";
    private static final String GOAL_ID = "goal_id";


    @Override
    public Task build(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID_TASK);
        String name = resultSet.getString(NAME_TASK);
        String description = resultSet.getString(DESCRIPTION);
        Date date = resultSet.getDate(DATE);
        byte isDone = resultSet.getByte(IS_DONE);
        long goal_id = resultSet.getLong(GOAL_ID);
        return new Task(id, name, description, date, isDone, goal_id);
    }
}
