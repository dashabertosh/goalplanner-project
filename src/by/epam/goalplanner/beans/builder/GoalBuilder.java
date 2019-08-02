package by.epam.goalplanner.beans.builder;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.constant.VariableConstant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class GoalBuilder implements Builder<Goal> {
    @Override
    public Goal build(ResultSet resultSet) {
        try {
            long id = resultSet.getLong(VariableConstant.ID.getName());
            String name = resultSet.getString(VariableConstant.NAME.getName());
            String description = resultSet.getString(VariableConstant.DESCRIPTION.getName());
            Date beginDate = resultSet.getDate(VariableConstant.BEGIN_DATE.getName());
            Date endDate = resultSet.getDate(VariableConstant.END_DATE.getName());
            long user_id = resultSet.getLong(VariableConstant.USER_ID.getName());
            long type_id = resultSet.getLong(VariableConstant.TYPE_ID.getName());
            return new Goal(id, name, description, beginDate, endDate, user_id, type_id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
