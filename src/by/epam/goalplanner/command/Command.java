package by.epam.goalplanner.command;

import by.epam.goalplanner.command.impl.ResultCommand;
import by.epam.goalplanner.exception.CommandException;
import by.epam.goalplanner.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    ResultCommand execute(HttpServletRequest req) throws CommandException;
}
