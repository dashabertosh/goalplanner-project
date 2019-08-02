package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.command.Cmd;
import by.epam.goalplanner.constant.VariableConstant;
import by.epam.goalplanner.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


public class LoginCmd implements Cmd {
    public static final String PAGE = "login";

    private final UserService userService;

    public LoginCmd(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResultCmd execute(HttpServletRequest req) {
        ResultCmd result;
        if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
            String login = req.getParameter(VariableConstant.LOGIN.getName());
            String password = req.getParameter(VariableConstant.PASSWORD.getName());

            Optional<User> user = userService.login(login, password);

            HttpSession session = req.getSession();
            if (user.isPresent()) {
                session.setAttribute(VariableConstant.USER.getName(), user.get());
                result = new ResultCmd(VariableConstant.DO_COMMAND_PROFILE.getName(), false);
            } else {
                result = new ResultCmd(VariableConstant.LOGIN_JSP.getName(), true);
            }
            return result;
        } else {
            result = new ResultCmd(VariableConstant.LOGIN_JSP.getName(), true);
            return result;
        }
    }
}
