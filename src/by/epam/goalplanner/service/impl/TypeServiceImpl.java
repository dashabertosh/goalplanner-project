package by.epam.goalplanner.service.impl;

import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.dao.TypeDao;
import by.epam.goalplanner.service.TypeService;

import java.util.List;

public class TypeServiceImpl implements TypeService {
    private final TypeDao typeDao;

    public TypeServiceImpl(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    @Override
    public List<Type> findAll() {
        return typeDao.getAll("");
    }

    @Override
    public boolean create(String name) {
        return typeDao.create(name);
    }

    @Override
    public long findIdByName(String name) {
        return typeDao.findIdByName(name);
    }
}
