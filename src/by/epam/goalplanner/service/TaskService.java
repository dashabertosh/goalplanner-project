package by.epam.goalplanner.service;

import by.epam.goalplanner.beans.Task;

import java.util.Date;
import java.util.List;

public interface TaskService extends Service<Task> {
    @Override
    List<Task> findAll();

    boolean delete(Task task);

    List<Task> findAll(String string);

    boolean create(String name, String description, Date date, byte done, long goal_id);
}
