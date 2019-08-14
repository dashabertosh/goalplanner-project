package by.epam.goalplanner.validate;

import by.epam.goalplanner.beans.Goal;

import java.util.Date;

public class Validator {
    private static final String REGULAR_PASSWORD = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    private static final String REGULAR_LOGIN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";
    private static final String REGULAR_NAME = "^[A-ZÀ-ß][a-zà-ÿ]{3,}";
    private static final String REGULAR_GOAL_TASK_NAME = "([à-ÿ-ß¸¨azA-Z0-9]+){1,45}";
    private static final String REGULAR_GOAL_TASK_DESCRIPTION = "([à-ÿ-ß¸¨azA-Z0-9]+){1,100}";

    public static String validateSignUp(String name, String login, String password) {
        if (name.isEmpty()) {
            return ValidateConstant.NAME_EMPTY.getName();
        }

        if (login.isEmpty()) {
            return ValidateConstant.LOGIN_EMPTY.getName();
        }

        if (password.isEmpty()) {
            return ValidateConstant.PASSWORD_EMPTY.getName();
        }

        if (!password.matches(REGULAR_PASSWORD)) {
            return ValidateConstant.PASSWORD_NOT_CORRECT.getName();
        }

        if (!login.matches(REGULAR_LOGIN)) {
            return ValidateConstant.LOGIN_NOT_CORRECT.getName();
        }

        if (!name.matches(REGULAR_NAME)) {
            return ValidateConstant.NAME_NOT_CORRECT.getName();
        }

        return null;
    }

    public static String validateLogin(String login, String password) {
        if (login.isEmpty()) {
            return ValidateConstant.LOGIN_EMPTY.getName();
        }

        if (password.isEmpty()) {
            return ValidateConstant.PASSWORD_EMPTY.getName();
        }

        return null;
    }

    public static String validateProfile(String name, String description, Date beginDate, Date endDate) {
        if (name.isEmpty()) {
            return ValidateConstant.NAME_GOAL_OR_TASK_EMPTY.getName();
        }

        if (description.isEmpty()) {
            return ValidateConstant.DESCRIPTION_EMPTY.getName();
        }

        if (beginDate == null) {
            return ValidateConstant.BEGIN_DATE_EMPTY.getName();
        }

        if (endDate == null) {
            return ValidateConstant.END_DATE_EMPTY.getName();
        }

        if (beginDate.getTime() > endDate.getTime()) {
            return ValidateConstant.DATES_NOT_CORRECT.getName();
        }

        if (name.length() > 45) {
            return ValidateConstant.NAME_NOT_CORRECT.getName();
        }

        if (description.length() > 100) {
            return ValidateConstant.DESCRIPTION_NOT_CORRECT.getName();
        }

        return null;
    }

    public static String validateCreateTask(String name, String description, Date date, Goal goal) {
        if (name.isEmpty()) {
            return ValidateConstant.NAME_GOAL_OR_TASK_EMPTY.getName();
        }

        if (description.isEmpty()) {
            return ValidateConstant.DESCRIPTION_EMPTY.getName();
        }

        if (date == null) {
            return ValidateConstant.TASK_DATE_EMPTY.getName();
        }

        if ((date.getTime() < goal.getBeginDate().getTime()) || date.getTime() > goal.getEndDate().getTime()) {
            return ValidateConstant.TASK_DATE_NOT_CORRECT.getName();
        }

        if (name.length() > 45) {
            return ValidateConstant.NAME_NOT_CORRECT.getName();
        }

        if (description.length() > 100) {
            return ValidateConstant.DESCRIPTION_NOT_CORRECT.getName();
        }

        return null;
    }
}
