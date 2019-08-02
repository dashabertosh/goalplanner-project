package by.epam.goalplanner.constant;

public enum  VariableConstant {
    INSTANCE(""),

    COMMAND("command"),
    POST("POST"),
    CREATE_GOAL_JSP("createGoal.jsp"),
    CREATE_TASK_JSP("createTask.jsp"),
    SIGN_UP_JSP("SignUp.jsp"),
    LOGIN_JSP("login.jsp"),
    PROFILE_JSP("profile.jsp"),
    UPDATE_TASK("update_task"),
    LOGIN("login"),
    PASSWORD("password"),
    ID("id"),
    NAME("name"),
    DONE("done"),
    DESCRIPTION("description"),
    FORMAT_DATE("dd/MM/yyyy"),
    BEGIN_DATE("begin_date"),
    DATE("date"),
    END_DATE("end_date"),
    TYPE_NAME("type_name"),
    ROLE_ID("role_id"),
    USER_ID("user_id"),
    GOAL_ID("goal_id"),
    TYPE_ID("type_id"),
    UPDATE_GOAL("update_goal"),
    DELETE_GOAL("delete_goal"),
    GOALS("goals"),
    USER("user"),
    DO_COMMAND_PROFILE("do?command=profile");

    private String name;

    VariableConstant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
