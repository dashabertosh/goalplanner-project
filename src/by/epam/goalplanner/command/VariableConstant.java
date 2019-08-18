package by.epam.goalplanner.command;

public enum  VariableConstant {

    COMMAND("command"),
    MESSAGE("message"),
    CREATE_NEW_TYPE("Create a new category"),
    CREATE_NEW_TYPE_RU("Создать новую категорию"),
    CREATE_NEW_GOAL("Create a new goal"),
    CREATE_NEW_GOAL_RU("Создать новую цель"),
    POST("POST"),
    NEW_TYPE("newType"),
    TYPES("types"),
    CREATE_GOAL_JSP("createGoal.jsp"),
    CREATE_TASK_JSP("createTask.jsp"),
    SIGN_UP_JSP("signUp.jsp"),
    ADMIN_JSP("admin.jsp"),
    LOGIN_JSP("login.jsp"),
    PROFILE_JSP("profile.jsp"),
    UPDATE_TASK("update_task"),
    FORMAT_DATE("yyyy-MM-dd"),
    UPDATE_GOAL("update_goal"),
    DELETE_TYPE("delete_type"),
    UPDATE("update"),
    DELETE("delete"),
    DELETE_GOAL("delete_goal"),
    GOALS("goals"),
    USER("user"),
    DO_COMMAND_ADMIN("do?command=admin"),
    DO_COMMAND_PROFILE("do?command=profile"),
    DO_COMMAND_CREATE_GOAL("do?command=createGoal"),
    DO_COMMAND_LOGIN("do?command=login");

    private String name;

    VariableConstant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
