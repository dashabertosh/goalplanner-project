package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.command.Cmd;
import by.epam.goalplanner.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class IndexCmd implements Cmd {
    private final UserService userService;

    public IndexCmd(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResultCmd execute(HttpServletRequest req) {
        List<User> users = userService.findAll();
        req.setAttribute("users", users );
        return null;
    }
}
