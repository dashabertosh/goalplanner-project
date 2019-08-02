package by.epam.goalplanner.controller;

import by.epam.goalplanner.command.Cmd;
import by.epam.goalplanner.command.FactoryCmd;
import by.epam.goalplanner.command.impl.ResultCmd;
import by.epam.goalplanner.constant.VariableConstant;
import by.epam.goalplanner.exception.CommandException;
import by.epam.goalplanner.exception.DaoException;
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
//    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void init() {
        try{
            ConnectionPool.getInstance();
        } catch (RuntimeException e) {
//            LOGGER.fatal("No database connection established.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (CommandException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (CommandException | DaoException e) {
            e.printStackTrace();
        }
    }

    private void process( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, CommandException, DaoException {
        String stringCmd = req.getParameter(VariableConstant.COMMAND.getName());
        try {
//            LOGGER.debug("hhh");
            FactoryCmd factoryCmd = FactoryCmd.getInstance();
            Cmd cmd = factoryCmd.create(stringCmd);
            ResultCmd resultCmd = cmd.execute(req);
            dispatch(req, resp, resultCmd);
        } catch (Exception e) {
          //  log();
            resp.sendRedirect(stringCmd);
        }
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, ResultCmd resultCmd) throws ServletException, IOException {
        String forwardUrl = resultCmd.getUrl();
        if (resultCmd.isForward()) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(forwardUrl);
            requestDispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(forwardUrl);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().closePool();
    }
}
