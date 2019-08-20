package by.epam.goalplanner.service.impl;

import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.dao.GoalDao;
import by.epam.goalplanner.dao.TypeDao;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.TypeService;

import java.util.List;

public class TypeServiceImpl implements TypeService {
    private final TypeDao typeDao;
    private final GoalDao goalDao;

    public TypeServiceImpl(TypeDao typeDao, GoalDao goalDao) {
        this.typeDao = typeDao;
        this.goalDao = goalDao;
    }

    @Override
    public List<Type> findAll() throws ServiceException {
        try {
            return typeDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Type> findAll(long id) throws ServiceException {
        String type = String.valueOf(id);
        try {
            return typeDao.findAll(type);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(long id) throws ServiceException {
        try {
            goalDao.deleteWithType(id);
            return typeDao.delete(id);
        } catch (DaoException e) {
            throw new  ServiceException(e);
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
    public List<Type> findSomeTypes() throws ServiceException {
        try {
            return typeDao.findSomeTypes();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Type> findIdTypeById(long id) throws ServiceException {
        try {
            return typeDao.findIdTypeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
