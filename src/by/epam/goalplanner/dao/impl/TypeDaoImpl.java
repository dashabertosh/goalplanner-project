package by.epam.goalplanner.dao.impl;

import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.dao.builder.Builder;
import by.epam.goalplanner.dao.SqlConstant;
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
        List<Type> type = executeQuery(SqlConstant.SELECT_ID_BY_NAME.getName(), name);
        return type.get(0).getId();
    }

    @Override
    public List<Type> findSomeTypes() throws DaoException {
        return executeQuery(SqlConstant.SELECT_SOME_TYPES.getName());
    }

    @Override
    public List<Type> findIdTypeById(long id) throws DaoException {
        List<Type> type = executeQuery(SqlConstant.SELECT_TYPE_BY_ID.getName(), id);
        return type;
    }

    @Override
    public boolean delete(long id) throws DaoException {
        return executeUpdate(SqlConstant.DELETE_TYPE.getName(), id);
    }

    @Override
    public List<Type> findAll(String sql) throws DaoException {
        return executeQuery(SqlConstant.SELECT_ALL_TYPES_WHERE.getName(), sql);
    }

    @Override
    public List<Type> findAll() throws DaoException {
        return executeQuery(SqlConstant.SELECT_ALL_TYPES.getName());
    }
}
