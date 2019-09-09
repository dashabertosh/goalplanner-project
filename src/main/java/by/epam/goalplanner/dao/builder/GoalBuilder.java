package by.epam.goalplanner.dao.builder;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.dao.DbConstant;
import by.epam.goalplanner.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class GoalBuilder implements Builder<Goal> {
    @Override
    public Goal build(ResultSet resultSet) throws DaoException {
        try {
            long id = resultSet.getLong(DbConstant.ID.getName());
            String name = resultSet.getString(DbConstant.NAME.getName());
            String description = resultSet.getString(DbConstant.DESCRIPTION.getName());
            Date beginDate = resultSet.getDate(DbConstant.BEGIN_DATE.getName());
            Date endDate = resultSet.getDate(DbConstant.END_DATE.getName());
            long user_id = resultSet.getLong(DbConstant.USER_ID.getName());
            long type_id = resultSet.getLong(DbConstant.TYPE_ID.getName());
            return new Goal(id, name, description, beginDate, endDate, user_id, type_id);
        } catch (SQLException e) {
            throw new DaoException("Entity assembly failed", e);
        }
    }


}
