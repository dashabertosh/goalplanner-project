package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.command.Command;
import by.epam.goalplanner.command.VariableConstant;
import by.epam.goalplanner.dao.DbConstant;
import by.epam.goalplanner.exception.CommandException;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.UserService;
import by.epam.goalplanner.validate.ValidateConstant;
import by.epam.goalplanner.validate.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String PAGE = "login";

    private final UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResultCommand execute(HttpServletRequest req) throws CommandException {
        ResultCommand result;
        try {
            if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
                LOGGER.debug("Command " + PAGE + "  began to execute.");
                String login = req.getParameter(DbConstant.LOGIN.getName());
                String password = req.getParameter(DbConstant.PASSWORD.getName());

                String message = Validator.validateLogin(login, password);
                if (message != null) {
                    LOGGER.debug("Data entered incorrectly.");
                    req.setAttribute(VariableConstant.MESSAGE.getName(), message);
                    result = new ResultCommand(VariableConstant.LOGIN_JSP.getName(), true);
                    return result;
                }

                Optional<User> user = userService.login(login, password);

                HttpSession session = req.getSession();
                if (user.isPresent()) {
                    session.setAttribute(VariableConstant.USER.getName(), user.get());
                    result = new ResultCommand(VariableConstant.DO_COMMAND_PROFILE.getName(), false);
                } else {
                    LOGGER.debug("User is not found.");
                    String noUser = ValidateConstant.NO_USER.getName();
                    req.setAttribute(VariableConstant.MESSAGE.getName(), noUser);
                    result = new ResultCommand(VariableConstant.LOGIN_JSP.getName(), true);
                }
                return result;
            } else {
                result = new ResultCommand(VariableConstant.LOGIN_JSP.getName(), true);
                return result;
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
