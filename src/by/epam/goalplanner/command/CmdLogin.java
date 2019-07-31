package by.epam.goalplanner.command;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;


public class CmdLogin implements Cmd {
    public static final String PAGE = "login";

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private final UserService userService;

    public CmdLogin(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CmdResult execute(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        CmdResult result;
        if (req.getMethod().equalsIgnoreCase("POST")) {
            String login = req.getParameter(LOGIN);
            String password = req.getParameter(PASSWORD);

            Optional<User> user = userService.login(login, password);

            HttpSession session = req.getSession();
            if (user.isPresent()) {
                session.setAttribute("user", user.get());
                result = new CmdResult("do?command=profile", false);
            } else {
                result = new CmdResult("login.jsp", true);
            }
            return result;
        } else {
            result = new CmdResult("login.jsp", true);
            return result;
        }
    }
}
