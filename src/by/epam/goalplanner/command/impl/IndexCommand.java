package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.command.Command;
import by.epam.goalplanner.exception.CommandException;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class IndexCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger();
    private final UserService userService;

    public IndexCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResultCommand execute(HttpServletRequest req) throws CommandException {
        try {
            List<User> users = userService.findAll();
            req.setAttribute("users", users);
            return null;
        } catch (ServiceException e) {
            LOGGER.error("Execution error", e);
            throw new CommandException(e);
        }
    }
}
