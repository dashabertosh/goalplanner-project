package by.epam.goalplanner.dao;

import by.epam.goalplanner.beans.Task;

import java.util.List;

public interface TaskDao extends BaseDao<Task> {
    List<Task> findTasksByDate(long date);
}
