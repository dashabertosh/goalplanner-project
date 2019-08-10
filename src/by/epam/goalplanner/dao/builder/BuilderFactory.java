package by.epam.goalplanner.dao.builder;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.beans.Task;
import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.beans.User;

public class BuilderFactory {
    public Builder<User> createUserBuilder() {
        return new UserBuilder();
    }

    public Builder<Task> createTaskBuilder() {
        return new TaskBuilder();
    }

    public Builder<Goal> createGoalBuilder() {
        return new GoalBuilder();
    }

    public Builder<Type> createTypeBuilder() { return new TypeBuilder(); }
}
