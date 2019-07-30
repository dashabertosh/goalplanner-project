package by.epam.goalplanner.beans.builder;

import by.epam.goalplanner.beans.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {
    private static final String ID_USER = "id";
    private static final String LOGIN_USER = "login";
    private static final String PASSWORD_USER = "password";
    private static final String NAME_USER = "name";
    private static final String ROLE_ID_USER = "role_id";

    @Override
    public User build(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(ID_USER);
        String login = resultSet.getString(LOGIN_USER);
        String password = resultSet.getString(PASSWORD_USER);
        String name = resultSet.getString(NAME_USER);
        long role_id = resultSet.getLong(ROLE_ID_USER);
        return new User(id, login, password, name, role_id);
    }
}
