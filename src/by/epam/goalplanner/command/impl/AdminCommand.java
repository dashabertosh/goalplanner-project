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

/**
 * AdminCommand page
 *
 * @author Dasha Lobkova on 2019-07-18.
 * @version 0.0.1
 */

public class AdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String PAGE = "admin";

    private final UserService userService;

    public AdminCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     * @param req DTO containing all data received with {@link javax.servlet.http.HttpServletRequest}
     * @return instance of {@link ResultCommand} that
     * forward to {@link AdminCommand}.PAGE
     *
     * @throws CommandException
     */
    @Override
    public ResultCommand execute(HttpServletRequest req) throws CommandException {
        ResultCommand result;
        try {
            if (!VerificationUser.checkUser(req)) return new ResultCommand(VariableConstant.LOGIN_JSP.getName(), true);
            if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
                LOGGER.debug("Command " + PAGE + "  began to execute.");
                long id = Long.parseLong(req.getParameter(DbConstant.ID.getName()));
                String login = req.getParameter(DbConstant.LOGIN.getName());
                String password = req.getParameter(DbConstant.PASSWORD.getName());
                String name = req.getParameter(DbConstant.NAME.getName());
                long roleId = Long.parseLong(req.getParameter(DbConstant.ROLE_ID.getName()));
                User user = new User(id, login, password, name, roleId);
                if (req.getParameter(VariableConstant.UPDATE.getName()) != null) {
                    userService.update(user);
                } else if (req.getParameter(VariableConstant.DELETE.getName()) != null) {
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
