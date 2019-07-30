package by.epam.goalplanner.dao;

import by.epam.goalplanner.beans.Type;

import java.util.List;

public interface TypeDao extends BaseDao<Type> {
    boolean create(String name);

    long findIdByName(String name);
}
