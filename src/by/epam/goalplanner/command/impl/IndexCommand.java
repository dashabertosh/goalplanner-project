package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.command.Command;
import by.epam.goalplanner.command.VariableConstant;
import javax.servlet.http.HttpServletRequest;

public class IndexCommand implements Command {

    @Override
    public ResultCommand execute(HttpServletRequest req) {
        return new ResultCommand(VariableConstant.HOME.getName(), true);
    }
}
