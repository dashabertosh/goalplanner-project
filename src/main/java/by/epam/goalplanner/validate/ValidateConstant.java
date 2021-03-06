package by.epam.goalplanner.validate;

public enum ValidateConstant {
    NAME_EMPTY("common.message.name.empty"),
    LOGIN_EMPTY("common.message.login.empty"),
    PASSWORD_EMPTY("common.message.password.empty"),
    PASSWORD_NOT_CORRECT("common.message.password.not.correct"),
    LOGIN_NOT_CORRECT("common.message.login.not.correct"),
    NAME_NOT_CORRECT("common.message.name.not.correct"),
    NO_USER("common.message.no.user"),
    NAME_GOAL_OR_TASK_EMPTY("common.message.name.empty"),
    DESCRIPTION_EMPTY("common.message.description.empty"),
    FIELDS_NULL("common.message.null"),
    TYPE_EXIST("common.message.type.exist"),
    CANT_DELETE("common.message.cant.delete"),
    DESCRIPTION_NOT_CORRECT("common.message.description.not.correct"),
    BEGIN_DATE_EMPTY("common.message.begin.date.empty"),
    END_DATE_EMPTY("common.message.end.date.empty"),
    DATES_NOT_CORRECT("common.message.dates.not.correct"),
    TASK_DATE_EMPTY("common.message.date.empty"),
    TASK_DATE_BIG("common.message.date.is.big"),
    TASK_DATE_NOT_CORRECT("common.message.task.date.not.correct"),
    USER_EXIST("common.message.user.exist"),
    ENTER_TYPE("goal.create.enterTypeName"),
    ;
    private String name;

    ValidateConstant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
