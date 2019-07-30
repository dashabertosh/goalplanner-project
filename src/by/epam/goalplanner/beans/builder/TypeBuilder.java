package by.epam.goalplanner.beans.builder;

import by.epam.goalplanner.beans.Type;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeBuilder implements Builder<Type> {
    private static final String ID = "id";
    private static final String NAME = "name";

    @Override
    public Type build(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        return new Type(id, name);
    }
}
