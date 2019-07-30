package by.epam.goalplanner.command;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class CmdIndex implements Cmd {
    private final UserService userService;

    public CmdIndex(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CmdResult execute(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        List<User> users = userService.findAll();
        req.setAttribute("users", users );
        return null;
    }
}
