package by.epam.goalplanner.command;

public enum  VariableConstant {

    COMMAND("command"),
    POST("POST"),
    NEW_TYPE("newType"),
    TYPES("types"),
    CREATE_EN("Create a new category"),
    CREATE_RU("Создать новую категорию"),
    CREATE_GOAL_JSP("createGoal.jsp"),
    CREATE_TASK_JSP("createTask.jsp"),
    SIGN_UP_JSP("signUp.jsp"),
    LOGIN_JSP("login.jsp"),
    PROFILE_JSP("profile.jsp"),
    UPDATE_TASK("update_task"),
    FORMAT_DATE("yyyy-MM-dd"),
    UPDATE_GOAL("update_goal"),
    DELETE_GOAL("delete_goal"),
    GOALS("goals"),
    USER("user"),
    DO_COMMAND_PROFILE("do?command=profile"),
    DO_COMMAND_LOGIN("do?command=login");

    private String name;

    VariableConstant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
