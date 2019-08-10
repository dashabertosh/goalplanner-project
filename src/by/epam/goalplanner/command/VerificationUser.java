package by.epam.goalplanner.command;

import by.epam.goalplanner.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class VerificationUser {
    public static boolean checkUser(HttpServletRequest req) {
        return findUser(req) != null;
    }

    public static User findUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            return (User) session.getAttribute(VariableConstant.USER.getName());
        }
        return null;
    }
}
