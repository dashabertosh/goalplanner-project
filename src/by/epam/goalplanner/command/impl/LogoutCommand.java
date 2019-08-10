package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.command.Command;
import by.epam.goalplanner.command.VariableConstant;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    public static final String PAGE = "logout";

    @Override
    public ResultCommand execute(HttpServletRequest req) {
        req.getSession().invalidate();
        return new ResultCommand(VariableConstant.DO_COMMAND_LOGIN.getName(), false);
    }
}
