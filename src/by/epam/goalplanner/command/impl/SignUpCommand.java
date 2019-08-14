package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.command.Command;
import by.epam.goalplanner.command.Hasher;
import by.epam.goalplanner.command.VariableConstant;
import by.epam.goalplanner.dao.DbConstant;
import by.epam.goalplanner.exception.CommandException;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.UserService;
import by.epam.goalplanner.validate.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SignUpCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String PAGE = "signUp";

    private final UserService userService;

    public SignUpCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResultCommand execute(HttpServletRequest req) throws CommandException {
        ResultCommand result;
        try {
            if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
                LOGGER.debug("Command " + PAGE + "  began to execute.");
                String login = getString(req, DbConstant.LOGIN.getName());
                String name = getString(req, DbConstant.NAME.getName());
                String password = getString(req, DbConstant.PASSWORD.getName());

                String message = Validator.validateSignUp(name, login, password);
                if (message != null) {
                    LOGGER.debug("Data entered incorrectly.");
                    req.setAttribute(VariableConstant.MESSAGE.getName(), message);
                    result = new ResultCommand(VariableConstant.SIGN_UP_JSP.getName(), true);// FIXME: 13.08.2019
                    return result;
                }

                String hashPassword = Hasher.getHash(password);
                boolean isUser = userService.create(login, hashPassword, name);
                if (isUser) {
                    Optional<User> user = userService.login(login, password);
                    req.getSession().setAttribute(VariableConstant.USER.getName(), user);
                }
                result = new ResultCommand(VariableConstant.PROFILE_JSP.getName(), true);
                return result;
            } else {
                result = new ResultCommand(VariableConstant.SIGN_UP_JSP.getName(), true);// FIXME: 13.08.2019
                return result;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    private String getString(HttpServletRequest req, String name, String pattern) {
        String result = req.getParameter(name);
        if (result.matches(pattern)) return result;
        return null;
    }

    private String getString(HttpServletRequest req, String name) {
        return getString(req, name, ".*");
    }

}
