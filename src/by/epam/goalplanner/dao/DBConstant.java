package by.epam.goalplanner.dao;

public enum DBConstant {

    LOGIN("login"),
    PASSWORD("password"),
    ID("id"),
    NAME("name"),
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

    DBConstant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }}
