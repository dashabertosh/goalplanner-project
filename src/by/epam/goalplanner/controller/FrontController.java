package by.epam.goalplanner.controller;

import by.epam.goalplanner.beans.builder.BuilderFactory;
import by.epam.goalplanner.command.Cmd;
import by.epam.goalplanner.command.CmdFactory;
import by.epam.goalplanner.command.CmdResult;
import by.epam.goalplanner.dao.DaoFactory;
import by.epam.goalplanner.pool.ConnectionPool;
import by.epam.goalplanner.pool.ProxyConnection;
import by.epam.goalplanner.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class FrontController extends HttpServlet {

    @Override
    public void init() {
        ConnectionPool.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stringCmd = req.getParameter("command");
        ProxyConnection connection;
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            CmdFactory cmdFactory = createCmdFactory(connection);
            Cmd cmd = cmdFactory.create(stringCmd);
            CmdResult cmdResult = cmd.execute(req, resp);
            dispatch(req, resp, cmdResult);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private CmdFactory createCmdFactory(ProxyConnection connection) {
        DaoFactory daoFactory = new DaoFactory(connection, new BuilderFactory());
        ServiceFactory serviceFactory = new ServiceFactory(daoFactory);
        return new CmdFactory(serviceFactory);
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, CmdResult cmdResult) throws ServletException, IOException {
        String forwardUrl = cmdResult.getUrl();
        if (cmdResult.isForward()) {
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
