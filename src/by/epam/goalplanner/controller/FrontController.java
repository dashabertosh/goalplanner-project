package by.epam.goalplanner.controller;

import by.epam.goalplanner.command.Command;
import by.epam.goalplanner.command.FactoryCommand;
import by.epam.goalplanner.command.impl.ResultCommand;
import by.epam.goalplanner.command.VariableConstant;
import by.epam.goalplanner.exception.CommandException;
import by.epam.goalplanner.exception.ConnectionPoolException;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FrontController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init() {
        try {
            ConnectionPool.getInstance();
        } catch (RuntimeException e) {
            LOGGER.fatal("No database connection established.", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (CommandException e) {
            LOGGER.error("Throw Command Exception", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (CommandException e) {
            LOGGER.error("Throw Command Exception", e);
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException {
        String stringCmd = req.getParameter(VariableConstant.COMMAND.getName());
        try {
            FactoryCommand factoryCommand = FactoryCommand.getInstance();
            Command command = factoryCommand.create(stringCmd);
            ResultCommand resultCommand = command.execute(req);
            dispatch(req, resp, resultCommand);
        } catch (ServiceException e) {
            LOGGER.error("Request execution process failed", e);
            resp.sendRedirect(VariableConstant.PROFILE_JSP.getName());
        }
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, ResultCommand resultCommand) throws ServletException, IOException {
        String forwardUrl = resultCommand.getUrl();
        if (resultCommand.isForward()) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(forwardUrl);
            requestDispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(forwardUrl);
        }
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.getInstance().closePool();
        } catch (ConnectionPoolException e) {
            LOGGER.error("Throw Connection Pool Exception", e);
        }
    }
}
