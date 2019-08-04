package by.epam.goalplanner.command;

import by.epam.goalplanner.command.impl.ResultCmd;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface Cmd {
    ResultCmd execute(HttpServletRequest req) throws DaoException, ServiceException;
}
