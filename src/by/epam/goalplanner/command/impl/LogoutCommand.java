package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.command.Command;
import by.epam.goalplanner.command.VariableConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * LogoutCommand page
 *
 * @author Dasha Lobkova on 2019-07-13.
 * @version 0.0.1
 */

public class LogoutCommand implements Command {
    public static final String PAGE = "logout";

    /**
     *
     * @param req DTO containing all data received with {@link javax.servlet.http.HttpServletRequest}
     * @return instance of {@link ResultCommand} that
     * forward to {@link LogoutCommand}.PAGE
     *
     */
    @Override
    public ResultCommand execute(HttpServletRequest req) {
        req.getSession().invalidate();
        return new ResultCommand(VariableConstant.DO_COMMAND_LOGIN.getName(), false);
    }
}
