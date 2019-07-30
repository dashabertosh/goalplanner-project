package by.epam.goalplanner.dao.impl;

import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.beans.builder.Builder;
import by.epam.goalplanner.dao.BaseDao;
import by.epam.goalplanner.dao.TypeDao;
import by.epam.goalplanner.pool.ProxyConnection;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TypeDaoImpl extends AbstractDao<Type> implements TypeDao {

    private static final String SELECT_ALL_TYPES_WHERE = "SELECT `id`, `name_type` FROM `type` ?";
    private static final String CREATE_TYPE = "INSERT INTO `type` (`name`) VALUES (?)";
    private static final String UPDATE_TYPE = "UPDATE `type` SET `name` = ? WHERE `type`.`id` = ?";
    private static  final String DELETE_TYPE = "DELETE FROM `type` WHERE `type`.`id` = ?";
    private static final String SELECT_ID_BY_NAME = "SELECT `id` FROM `type` WHERE `name` = ?";

    public TypeDaoImpl(ProxyConnection connection, Builder<Type> builder) {
        super(connection, builder);
    }

    @Override
    public boolean create(String name) {
        return executeUpdate(CREATE_TYPE, name);
    }

    @Override
    public boolean update(Type type) {
        return executeUpdate(UPDATE_TYPE, type.getName(), type.getId());
    }

    @Override
    public long findIdByName(String name) {
        List<Type> type = executeQuery(SELECT_ID_BY_NAME, name);
        return type.get(0).getId();
    }

    @Override
    public boolean delete(Type type) {
        return executeUpdate(DELETE_TYPE, type.getId());
    }

    @Override
    public Type read(long id) {
        String sqlSuffix = String.format("WHERE id=%d", id);
        List<Type> all = getAll(sqlSuffix);
        return all.size() > 0 ? all.get(0) : null;
    }

    @Override
    public List<Type> getAll(String sql) {
        return executeQuery(SELECT_ALL_TYPES_WHERE, sql);
    }


}
