package by.epam.goalplanner.dao;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.beans.Task;
import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.beans.builder.Builder;
import by.epam.goalplanner.beans.builder.BuilderFactory;
import by.epam.goalplanner.dao.impl.GoalDaoImpl;
import by.epam.goalplanner.dao.impl.TaskDaoImpl;
import by.epam.goalplanner.dao.impl.TypeDaoImpl;
import by.epam.goalplanner.dao.impl.UserDaoImpl;


public class DaoFactory {
    private final static DaoFactory DAO_FACTORY = new DaoFactory();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return DAO_FACTORY;
    }

    public UserDao createUserDao() {
        BuilderFactory builderFactory = new BuilderFactory();
        Builder<User> userBuilder = builderFactory.createUserBuilder();
        return new UserDaoImpl(userBuilder);
    }

    public GoalDao createGoalDao() {
        BuilderFactory builderFactory = new BuilderFactory();
        Builder<Goal> goalBuilder = builderFactory.createGoalBuilder();
        return new GoalDaoImpl(goalBuilder);
    }

    public TaskDao createTaskDao() {
        BuilderFactory builderFactory = new BuilderFactory();
        Builder<Task> taskBuilder = builderFactory.createTaskBuilder();
        return new TaskDaoImpl(taskBuilder);
    }

    public TypeDao createTypeDao() {
        BuilderFactory builderFactory = new BuilderFactory();
        Builder<Type> typeBuilder = builderFactory.createTypeBuilder();
        return new TypeDaoImpl(typeBuilder);
    }
}
