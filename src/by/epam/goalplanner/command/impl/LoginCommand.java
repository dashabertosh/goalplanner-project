package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.command.Command;
import by.epam.goalplanner.command.VariableConstant;
import by.epam.goalplanner.dao.DBConstant;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.UserService;
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
    public ResultCommand execute(HttpServletRequest req) throws ServiceException {
        ResultCommand result;
        if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
            String login = req.getParameter(DBConstant.LOGIN.getName());
            String password = req.getParameter(DBConstant.PASSWORD.getName());

            Optional<User> user = userService.login(login, password);

            HttpSession session = req.getSession();
            if (user.isPresent()) {
                session.setAttribute(VariableConstant.USER.getName(), user.get());
                result = new ResultCommand(VariableConstant.DO_COMMAND_PROFILE.getName(), false);
            } else {
                result = new ResultCommand(VariableConstant.LOGIN_JSP.getName(), true);
            }
            return result;
        } else {
            result = new ResultCommand(VariableConstant.LOGIN_JSP.getName(), true);
            return result;
        }
    }
}
