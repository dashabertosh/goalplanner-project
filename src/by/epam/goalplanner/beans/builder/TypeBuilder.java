package by.epam.goalplanner.beans.builder;

import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.constant.VariableConstant;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeBuilder implements Builder<Type> {
    @Override
    public Type build(ResultSet resultSet) {
        try {
            long id = resultSet.getLong(VariableConstant.ID.getName());
            String name = resultSet.getString(VariableConstant.NAME.getName());
            return new Type(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
