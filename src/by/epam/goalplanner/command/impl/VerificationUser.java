package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.constant.VariableConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

class VerificationUser {

    static boolean checkUser(HttpServletRequest req) {
        return findUser(req) != null;
    }

    static User findUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            return (User) session.getAttribute(VariableConstant.USER.getName());
        }
        return null;
    }
}
