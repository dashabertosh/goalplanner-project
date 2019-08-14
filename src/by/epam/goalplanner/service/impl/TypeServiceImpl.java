package by.epam.goalplanner.service.impl;

import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.dao.TypeDao;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.TypeService;

import java.util.List;

public class TypeServiceImpl implements TypeService {
    private final TypeDao typeDao;

    public TypeServiceImpl(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    @Override
    public List<Type> findAll() throws ServiceException {
        try {
            return typeDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean create(String name) throws ServiceException {
        try {
            return typeDao.create(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long findIdByName(String name) throws ServiceException {
        try {
            return typeDao.findIdByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long findIdTypeById(long id) throws ServiceException {
        try {
            return typeDao.findIdTypeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
