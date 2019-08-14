package by.epam.goalplanner.dao.impl;

import by.epam.goalplanner.beans.Role;
import by.epam.goalplanner.dao.RoleDao;
import by.epam.goalplanner.dao.SqlConstant;
import by.epam.goalplanner.dao.builder.Builder;
import by.epam.goalplanner.exception.DaoException;

import java.util.List;

public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
   public RoleDaoImpl(Builder<Role> builder) {super(builder);}

    @Override
    public List<Role> findAll() throws DaoException {
        return executeQuery(SqlConstant.FIND_ALL_ROLES.getName());
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean update(Role role){
        return false;
    }

    @Override
    public List<Role> findAll(String sql) throws DaoException {
        return null;
    }
}
