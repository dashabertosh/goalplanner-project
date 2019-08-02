package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.command.Cmd;
import by.epam.goalplanner.constant.VariableConstant;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SignUpCmd implements Cmd {
    public static final String PAGE = "signUp";

    private final UserService userService;

    public SignUpCmd(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResultCmd execute(HttpServletRequest req) throws DaoException {
        ResultCmd result;
        if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
            String login = getString(req, VariableConstant.LOGIN.getName());
            String name = getString(req, VariableConstant.NAME.getName());
            String password = getString(req, VariableConstant.PASSWORD.getName());

            //validation

            boolean isUser = userService.create(login, password, name);
            if (isUser) {
                Optional<User> user = userService.login(login, password);
                req.getSession().setAttribute(VariableConstant.USER.getName(), user);
            }
            result = new ResultCmd(VariableConstant.PROFILE_JSP.getName(), true);
            return result;
        } else {
            result = new ResultCmd(VariableConstant.SIGN_UP_JSP.getName(), true);
            return result;
        }
    }

    private String getString(HttpServletRequest req, String name, String pattern)  {
        String result = req.getParameter(name);
        if (result.matches(pattern))
            return result;
        return null;
    }
    private String getString(HttpServletRequest req, String name)  {
        return getString(req, name,".*");
    }

}
