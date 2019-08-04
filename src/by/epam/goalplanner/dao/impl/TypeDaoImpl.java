package by.epam.goalplanner.dao.impl;

import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.beans.builder.Builder;
import by.epam.goalplanner.constant.SqlConstant;
import by.epam.goalplanner.dao.TypeDao;
import by.epam.goalplanner.exception.DaoException;

import java.util.List;

public class TypeDaoImpl extends AbstractDao<Type> implements TypeDao {
    public TypeDaoImpl(Builder<Type> builder) {
        super(builder);
    }

    @Override
    public boolean create(String name) throws DaoException {
        return executeUpdate(SqlConstant.CREATE_TYPE.getName(), name);
    }

    @Override
    public boolean update(Type type) throws DaoException {
        return executeUpdate(SqlConstant.UPDATE_TYPE.getName(), type.getName(), type.getId());
    }

    @Override
    public long findIdByName(String name) throws DaoException {
        List<Type> type = executeQuery(SqlConstant.SELECT_TYPE_ID_BY_NAME.getName(), name);
        return type.get(0).getId();
    }

    @Override
    public boolean delete(Type type) throws DaoException {
        return executeUpdate(SqlConstant.DELETE_TYPE.getName(), type.getId());
    }

    @Override
    public Type read(long id) throws DaoException {
        String sqlSuffix = String.format(SqlConstant.WHERE_ID.getName(), id);
        List<Type> all = getAll(sqlSuffix);
        return all.size() > 0 ? all.get(0) : null;
    }

    @Override
    public List<Type> getAll(String sql) throws DaoException {
        return executeQuery(SqlConstant.SELECT_ALL_TYPES_WHERE.getName(), sql);
    }

    @Override
    public List<Type> getAll() {
        return null;
    }
}
