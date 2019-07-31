package by.epam.goalplanner.command;

import by.epam.goalplanner.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Util {

    static boolean checkUser(HttpServletRequest req) {
        return findUser(req) != null;
    }

    static User findUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Object oUser = session.getAttribute("user");
            if (oUser != null) {
                return (User) oUser;
            }
        }
        return null;
    }

}