package by.epam.goalplanner.command;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class CmdSignup implements Cmd {
    public static final String PAGE = "signup";

    private final UserService userService;

    public CmdSignup(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CmdResult execute(HttpServletRequest req, HttpServletResponse res) {
        CmdResult result;
        if (req.getMethod().equalsIgnoreCase("POST")) {
            String login = getString(req, "login");
            String name = getString(req, "name");
            String password = getString(req, "password");

            //validation

            boolean user1 = userService.create(login, password, name);
            if (user1) {
                Optional<User> user = userService.login(login, password);
                req.getSession().setAttribute("user", user);
            }
            result = new CmdResult("profile.jsp", true);
            return result;
        } else {
            result = new CmdResult("signup.jsp", true);
            return result;
        }
    }

    private String getString(HttpServletRequest req, String name, String pattern)  {
        String result = req.getParameter(name);
        if (result.matches(pattern))
            return result;
        return null;
    }
    private String getString(HttpServletRequest req, String name)  {
        return getString(req, name,".*");
    }

}
