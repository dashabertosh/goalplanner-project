package by.epam.goalplanner.service;

import by.epam.goalplanner.beans.Task;
import by.epam.goalplanner.exception.DaoException;

import java.util.Date;
import java.util.List;

public interface TaskService{
    List<Task> findAll();

    boolean delete(Task task) throws DaoException;

    List<Task> findAll(String string);

    boolean create(String name, String description, Date date, byte done, long goal_id);
}
