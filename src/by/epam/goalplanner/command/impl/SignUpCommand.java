package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.command.Command;
import by.epam.goalplanner.command.VariableConstant;
import by.epam.goalplanner.dao.DBConstant;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SignUpCommand implements Command {
    public static final String PAGE = "signUp";

    private final UserService userService;

    public SignUpCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResultCommand execute(HttpServletRequest req) throws ServiceException {
        ResultCommand result;
        if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
            String login = getString(req, DBConstant.LOGIN.getName());
            String name = getString(req, DBConstant.NAME.getName());
            String password = getString(req, DBConstant.PASSWORD.getName());

            //validation

            boolean isUser = userService.create(login, password, name);
            if (isUser) {
                Optional<User> user = userService.login(login, password);
                req.getSession().setAttribute(VariableConstant.USER.getName(), user);
            }
            result = new ResultCommand(VariableConstant.PROFILE_JSP.getName(), true);
            return result;
        } else {
            result = new ResultCommand(VariableConstant.SIGN_UP_JSP.getName(), true);
            return result;
        }
    }

    private String getString(HttpServletRequest req, String name, String pattern)  {
        String result = req.getParameter(name);
        if (result.matches(pattern)) return result;
        return null;
    }
    private String getString(HttpServletRequest req, String name)  {
        return getString(req, name,".*");
    }

}
