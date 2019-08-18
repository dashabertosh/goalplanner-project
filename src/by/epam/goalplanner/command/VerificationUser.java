package by.epam.goalplanner.command;

import by.epam.goalplanner.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * VerificationUser class
 *
 * @author Dasha Lobkova on 2019-07-10.
 * @version 0.0.1
 */

public class VerificationUser {
    public static boolean checkUser(HttpServletRequest req) {
        return findUser(req) != null;
    }

    /**
     *
     * @param req DTO containing all data received with {@link javax.servlet.http.HttpServletRequest}
     * @return instance of {@link User} if user in session
     */

    public static User findUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            return (User) session.getAttribute(VariableConstant.USER.getName());
        }
        return null;
    }
}
