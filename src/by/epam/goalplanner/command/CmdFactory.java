package by.epam.goalplanner.command;

import by.epam.goalplanner.service.*;

import javax.servlet.http.HttpServletRequest;

public class CmdFactory {
    private final ServiceFactory serviceFactory;

    public CmdFactory(ServiceFactory serviceFactory) {
        this.serviceFactory = serviceFactory;
    }

    public Cmd create(String cmd) {
        Cmd command;
        if (cmd == null) {
            UserService userService = serviceFactory.createUserService();
            return new CmdIndex(userService);
        }
        switch (cmd) {
            case CmdLogin.PAGE: {
                UserService userService = serviceFactory.createUserService();
                command = new CmdLogin(userService);
                break;
            }
            case CmdSignup.PAGE: {
                UserService userService = serviceFactory.createUserService();
                command = new CmdSignup(userService);
                break;
            }
            case CmdProfile.PAGE: {
                UserService userService = serviceFactory.createUserService();
                TaskService taskService = serviceFactory.createTaskService();
                GoalService goalService = serviceFactory.createGoalService();
                TypeService typeService = serviceFactory.createTypeService();
                command = new CmdProfile(userService, goalService, taskService, typeService);
                break;
            }
            default: {
                UserService userService = serviceFactory.createUserService();
                return new CmdIndex(userService);
            }
        }
        return command;
    }

    static String getString(HttpServletRequest req, String name, String pattern) {
        String result = req.getParameter(name);
        if (result.matches(pattern))
            return result;
        return null;
    }
}
