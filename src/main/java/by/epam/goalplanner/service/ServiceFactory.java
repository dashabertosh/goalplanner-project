package by.epam.goalplanner.service;

import by.epam.goalplanner.dao.*;
import by.epam.goalplanner.service.impl.GoalServiceImpl;
import by.epam.goalplanner.service.impl.TaskServiceImpl;
import by.epam.goalplanner.service.impl.TypeServiceImpl;
import by.epam.goalplanner.service.impl.UserServiceImpl;


public class ServiceFactory {
    private final static ServiceFactory SERVICE_FACTORY = new ServiceFactory();

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
       return SERVICE_FACTORY;
    }

    public UserService createUserService() {
        UserDao userDao = daoFactory.createUserDao();
        RoleDao roleDao = daoFactory.createRoleDao();
        TaskDao taskDao = daoFactory.createTaskDao();
        GoalDao goalDao = daoFactory.createGoalDao();
        return new UserServiceImpl(userDao, roleDao, goalDao, taskDao);
    }

    public TaskService createTaskService() {
        TaskDao taskDao = daoFactory.createTaskDao();
        return new TaskServiceImpl(taskDao);
    }

    public GoalService createGoalService() {
        GoalDao goalDao = daoFactory.createGoalDao();
        TaskDao taskDao = daoFactory.createTaskDao();
        return new GoalServiceImpl(goalDao, taskDao);
    }

    public TypeService createTypeService() {
        TypeDao typeDao = daoFactory.createTypeDao();
        GoalDao goalDao = daoFactory.createGoalDao();
        return new TypeServiceImpl(typeDao, goalDao);
    }
}
