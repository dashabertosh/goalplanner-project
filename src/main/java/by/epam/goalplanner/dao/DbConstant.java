package by.epam.goalplanner.dao;

public enum DbConstant {

    LOGIN("login"),
    PASSWORD("password"),
    ID("id"),
    ID_GOAL("id_goal"),
    ID_TASK("id_task"),
    NAME("name"),
    NAME_GOAL("name_goal"),
    CREATE_TYPE("create_type"),
    DESCRIPTION("description"),
    BEGIN_DATE("begin_date"),
    DATE("date"),
    END_DATE("end_date"),
    TYPE_NAME("type_name"),
    ROLE_ID("role_id"),
    USER_ID("user_id"),
    GOAL_ID("goal_id"),
    TYPE_ID("type_id");

    private String name;

    DbConstant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }}
