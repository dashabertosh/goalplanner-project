package by.epam.goalplanner.beans.builder;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.constant.VariableConstant;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {
    @Override
    public User build(ResultSet resultSet) {
        try {
            long id = resultSet.getLong(VariableConstant.ID.getName());
            String login = resultSet.getString(VariableConstant.LOGIN.getName());
            String password = resultSet.getString(VariableConstant.PASSWORD.getName());
            String name = resultSet.getString(VariableConstant.NAME.getName());
            long role_id = resultSet.getLong(VariableConstant.ROLE_ID.getName());
            return new User(id, login, password, name, role_id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
