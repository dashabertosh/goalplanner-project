package by.epam.goalplanner.beans.builder;

import by.epam.goalplanner.beans.Goal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class GoalBuilder implements Builder<Goal> {
    private static final String ID_GOAL = "id";
    private static final String NAME_GOAL = "name";
    private static final String DESCRIPTION = "description";
    private static final String BEGIN_DATE = "begin_date";
    private static final String END_DATE = "end_date";
    private static final String USER_ID = "user_id";
    private static final String TYPE_ID = "type_id";

    @Override
    public Goal build(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID_GOAL);
        String name = resultSet.getString(NAME_GOAL);
        String description = resultSet.getString(DESCRIPTION);
        Date beginDate = resultSet.getDate(BEGIN_DATE);
        Date endDate = resultSet.getDate(END_DATE);
        long user_id = resultSet.getLong(USER_ID);
        long type_id = resultSet.getLong(TYPE_ID);
        return new Goal(id, name, description, beginDate, endDate, user_id, type_id);
    }
}
