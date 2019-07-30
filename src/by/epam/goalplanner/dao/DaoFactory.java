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

import by.epam.goalplanner.pool.ProxyConnection;

public class DaoFactory {
    private final ProxyConnection connection;
    private final BuilderFactory builderFactory;

    public DaoFactory(ProxyConnection connection, BuilderFactory builderFactory) {
        this.connection = connection;
        this.builderFactory = builderFactory;
    }

    public UserDao createUserDao() {
        Builder<User> userBuilder = builderFactory.createUserBuilder();
        return new UserDaoImpl(connection, userBuilder);
    }

    public GoalDao createGoalDao() {
        Builder<Goal> goalBuilder = builderFactory.createGoalBuilder();
        return new GoalDaoImpl(connection, goalBuilder);
    }

    public TaskDao createTaskDao() {
        Builder<Task> taskBuilder = builderFactory.createTaskBuilder();
        return new TaskDaoImpl(connection, taskBuilder);
    }

    public  TypeDao createTypeDao() {
        Builder<Type> typeBuilder = builderFactory.createTypeBuilder();
        return new TypeDaoImpl(connection, typeBuilder);
    }
}
