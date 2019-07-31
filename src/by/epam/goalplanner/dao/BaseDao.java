package by.epam.goalplanner.dao;

import java.util.List;

public interface BaseDao<Type> {
    boolean update(Type type);
    boolean delete(Type type);
    Type read(long id);
    List<Type> getAll(String sql);

    List<Type> getAll();
}
