package by.epam.goalplanner.dao;

import by.epam.goalplanner.beans.*;
import by.epam.goalplanner.dao.builder.Builder;
import by.epam.goalplanner.dao.builder.BuilderFactory;
import by.epam.goalplanner.dao.impl.*;
import by.epam.goalplanner.exception.DaoException;

import java.util.List;


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

    public RoleDao createRoleDao() {
        BuilderFactory builderFactory = new BuilderFactory();
        Builder<Role> roleBuilder = builderFactory.createRoleBuilder();
        return new RoleDaoImpl(roleBuilder);
    }

}
