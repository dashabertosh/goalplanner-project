package by.epam.goalplanner.dao.impl;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.beans.builder.Builder;
import by.epam.goalplanner.dao.UserDao;
import by.epam.goalplanner.pool.ProxyConnection;

import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String FIND_USER_BY_NAME = "SELECT name FROM `user` WHERE user.name = ?";
    private static final String SELECT_ALL_USERS_WHERE = "SELECT `id`, `login`, `password`, `name`, `role_id` FROM `user` ?";
    private static final String CREATE_USER = "INSERT INTO `user` (`login`, `password`, `name`,  `role_id`) VALUES (?, ?, ?, ?)";
    private static final String DELETE_USER = "DELETE FROM `user` WHERE `goalplanner`.`user`.`id` = ?";
    private static final String UPDATE_USER = "UPDATE `user` SET `login` = '?', `password` = '?', `name` = '?', `roles_id` = '?' WHERE `user`.`id` = ?";
    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT id, login, password, name, role_id FROM `user` WHERE user.login = ? AND user.password = ?";

    public UserDaoImpl(ProxyConnection connection, Builder<User> builder) {
        super(connection, builder);
    }


    @Override
    public boolean create(String login, String password, String name) {
        return executeUpdate(CREATE_USER, login, password, name, 1);
    }
    @Override
    public boolean delete(User user) {
        return executeUpdate(DELETE_USER, user.getId());
    }

    @Override
    public boolean update(User user) {

        return executeUpdate(UPDATE_USER, user.getLogin(),
                user.getPassword(), user.getName(), user.getRoles_ID(),
                user.getId());
    }

    @Override
    public User read(long id) {
        String sqlSuffix = String.format("WHERE id=%d", id);
        List<User> all = getAll(sqlSuffix);
        return all.size() > 0 ? all.get(0) : null;
    }

    @Override
    public List<User> getAll(String sql) {
        return executeQuery(SELECT_ALL_USERS_WHERE, sql);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) {
        return executeQueryForSingleResult(SELECT_USER_BY_LOGIN_AND_PASSWORD, login, password);
    }

    @Override
    public List<User> findUserByName(String name) {
        return executeQuery(FIND_USER_BY_NAME, name);
    }
}
