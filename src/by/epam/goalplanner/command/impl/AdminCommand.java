package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.Role;
import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.command.Command;
import by.epam.goalplanner.command.VariableConstant;
import by.epam.goalplanner.command.VerificationUser;
import by.epam.goalplanner.dao.DbConstant;
import by.epam.goalplanner.exception.CommandException;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String PAGE = "admin";

    private final UserService userService;

    public AdminCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResultCommand execute(HttpServletRequest req) throws CommandException {
        ResultCommand result;
        try {
            if (!VerificationUser.checkUser(req)) return new ResultCommand(VariableConstant.LOGIN_JSP.getName(), true);
            if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
                LOGGER.debug("Command " + PAGE + "  began to execute.");
                String login = req.getParameter(DbConstant.LOGIN.getName());
                String password = req.getParameter(DbConstant.PASSWORD.getName());

                User user = userService.login(login, password).get(0);
                if (req.getParameter(VariableConstant.UPDATE_GOAL.getName()) != null) {
                    userService.update(user);
                } else if (req.getParameter(VariableConstant.DELETE_GOAL.getName()) != null) {
                    userService.delete(user.getId());
                }
            }
            showInfo(req);
            result = new ResultCommand(VariableConstant.ADMIN_JSP.getName(), true);
            return result;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    private void showInfo(HttpServletRequest req) throws ServiceException {
        List<User> users = userService.findAll();
        List<Role> roles = userService.findAllRoles();
        req.setAttribute("users", users);
        req.setAttribute("roles", roles);
    }
}
