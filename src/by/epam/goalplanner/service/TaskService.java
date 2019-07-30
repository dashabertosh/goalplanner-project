package by.epam.goalplanner.service;

import by.epam.goalplanner.beans.Task;

import java.util.List;

public interface TaskService extends Service<Task> {
    @Override
    List<Task> findAll();

    boolean delete(Task task);

    List<Task> findAll(String string);
}
