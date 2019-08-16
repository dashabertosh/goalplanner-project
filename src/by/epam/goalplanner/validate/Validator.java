package by.epam.goalplanner.validate;

import by.epam.goalplanner.beans.Goal;
import java.util.Date;

public class Validator {
    private static final String REGULAR_PASSWORD = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    private static final String REGULAR_LOGIN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$";

    public static String validateSignUp(String name, String login, String password) {
        if (name.trim().isEmpty()) {
            return ValidateConstant.NAME_EMPTY.getName();
        }

        if (login.trim().isEmpty()) {
            return ValidateConstant.LOGIN_EMPTY.getName();
        }

        if (password.trim().isEmpty()) {
            return ValidateConstant.PASSWORD_EMPTY.getName();
        }

        if (!password.matches(REGULAR_PASSWORD)) {
            return ValidateConstant.PASSWORD_NOT_CORRECT.getName();
        }

        if (!login.matches(REGULAR_LOGIN)) {
            return ValidateConstant.LOGIN_NOT_CORRECT.getName();
        }

        if (name.length() > 45) {
            return ValidateConstant.NAME_NOT_CORRECT.getName();
        }

        return null;
    }

    public static String validateLogin(String login, String password) {
        if (login.trim().isEmpty()) {
            return ValidateConstant.LOGIN_EMPTY.getName();
        }

        if (password.trim().isEmpty()) {
            return ValidateConstant.PASSWORD_EMPTY.getName();
        }

        return null;
    }

    public static String validateProfile(String name, String description, Date beginDate, Date endDate) {
        if (name.trim().isEmpty()) {
            return ValidateConstant.NAME_GOAL_OR_TASK_EMPTY.getName();
        }

        if (description.trim().isEmpty()) {
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
        if (name.trim().isEmpty()) {
            return ValidateConstant.NAME_GOAL_OR_TASK_EMPTY.getName();
        }

        if (description.trim().isEmpty()) {
            return ValidateConstant.DESCRIPTION_EMPTY.getName();
        }

        if (date.getTime() > 2147483647) {
            return ValidateConstant.TASK_DATE_BIG.getName();
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
