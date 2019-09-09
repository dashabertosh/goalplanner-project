package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.command.Command;
import by.epam.goalplanner.command.Hasher;
import by.epam.goalplanner.command.VariableConstant;
import by.epam.goalplanner.dao.DbConstant;
import by.epam.goalplanner.exception.CommandException;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.UserService;
import by.epam.goalplanner.validate.ValidateConstant;
import by.epam.goalplanner.validate.Validator;
import by.epam.goalplanner.validate.XssProtection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * SignUpCommand page
 *
 * @author Dasha Lobkova on 2019-07-18.
 * @version 0.0.1
 */

public class SignUpCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String PAGE = "signUp";

    private final UserService userService;

    public SignUpCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @param req DTO containing all data received with {@link javax.servlet.http.HttpServletRequest}
     * @return instance of {@link ResultCommand} that
     * forward to {@link SignUpCommand}.PAGE
     *
     * @throws CommandException
     */
    @Override
    public ResultCommand execute(HttpServletRequest req) throws CommandException {
        ResultCommand result;
        try {
            if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
                LOGGER.debug("Command " + PAGE + "  began to execute.");
                String login = getString(req, DbConstant.LOGIN.getName());
                String name = getString(req, DbConstant.NAME.getName());
                String password = getString(req, DbConstant.PASSWORD.getName());

                XssProtection protection = XssProtection.signUpProtection(name, login, password);
                String message = Validator.validateSignUp(name, login, password);
                if (message != null) {
                    LOGGER.debug("Data entered incorrectly.");
                    req.setAttribute(VariableConstant.MESSAGE.getName(), message);
                    result = new ResultCommand(VariableConstant.SIGN_UP_JSP.getName(), true);
                    return result;
                }

                String hashPassword = Hasher.getHash(protection.getPassword());
                if (userService.login(protection.getLogin(), protection.getPassword()).isEmpty()) {
                    boolean isUser = userService.create(protection.getLogin(), hashPassword, protection.getName());
                    if (isUser) {
                        User user = userService.login(protection.getLogin(), protection.getPassword()).get(0);
                        req.getSession().setAttribute(VariableConstant.USER.getName(), user);
                    }
                    result = new ResultCommand(VariableConstant.PROFILE_JSP.getName(), true);
                    return result;
                } else {
                    String messageExist = ValidateConstant.USER_EXIST.getName();
                    req.setAttribute(VariableConstant.MESSAGE.getName(), messageExist);
                    result = new ResultCommand(VariableConstant.SIGN_UP_JSP.getName(), true);
                    return result;
                }
            } else {
                result = new ResultCommand(VariableConstant.SIGN_UP_JSP.getName(), true);
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
