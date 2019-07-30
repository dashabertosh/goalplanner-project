package by.epam.goalplanner.service.impl;

import by.epam.goalplanner.beans.Task;
import by.epam.goalplanner.dao.TaskDao;
import by.epam.goalplanner.service.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    private final TaskDao taskDao;

    public TaskServiceImpl(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public List<Task> findAll() {
        return taskDao.getAll();
    }

    @Override
    public boolean delete(Task task) {
        return taskDao.delete(task);
    }

    @Override
    public List<Task> findAll(String string) {
        return taskDao.getAll(string);
    }
}
