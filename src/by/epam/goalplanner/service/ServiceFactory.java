package by.epam.goalplanner.service;

import by.epam.goalplanner.dao.*;
import by.epam.goalplanner.service.impl.GoalServiceImpl;
import by.epam.goalplanner.service.impl.TaskServiceImpl;
import by.epam.goalplanner.service.impl.TypeServiceImpl;
import by.epam.goalplanner.service.impl.UserServiceImpl;

public class ServiceFactory {
    private final DaoFactory daoFactory;

    public ServiceFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public UserService createUserService() {
        UserDao userDao = daoFactory.createUserDao();
        return new UserServiceImpl(userDao);
    }

    public TaskService createTaskService() {
        TaskDao taskDao = daoFactory.createTaskDao();
        return new TaskServiceImpl(taskDao);
    }

    public GoalService createGoalService() {
        GoalDao goalDao = daoFactory.createGoalDao();
        return new GoalServiceImpl(goalDao);
    }

    public TypeService createTypeService() {
        TypeDao typeDao = daoFactory.createTypeDao();
        return new TypeServiceImpl(typeDao);
    }

}