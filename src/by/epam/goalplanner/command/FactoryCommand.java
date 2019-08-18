package by.epam.goalplanner.command;

import by.epam.goalplanner.command.impl.*;
import by.epam.goalplanner.service.*;

/**
 * FactoryCommand page
 *
 * @author Dasha Lobkova on 2019-07-18.
 * @version 0.0.1
 */

public class FactoryCommand {
    private final static FactoryCommand FACTORY_CMD = new FactoryCommand();

    private FactoryCommand() {
    }

    public static FactoryCommand getInstance() {
        return FACTORY_CMD;
    }

    /**
     *
     * @param cmd name of command
     * @return instance of {@link Command} by command name
     */

    public Command create(String cmd) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        Command command;
        if (cmd == null) {
            UserService userService = serviceFactory.createUserService();
            return new LoginCommand(userService);
        }
        switch (cmd) {
            case LoginCommand.PAGE: {
                UserService userService = serviceFactory.createUserService();
                command = new LoginCommand(userService);
                break;
            }
            case SignUpCommand.PAGE: {
                UserService userService = serviceFactory.createUserService();
                command = new SignUpCommand(userService);
                break;
            }
            case ProfileCommand.PAGE: {
                TaskService taskService = serviceFactory.createTaskService();
                GoalService goalService = serviceFactory.createGoalService();
                TypeService typeService = serviceFactory.createTypeService();
                command = new ProfileCommand(goalService, taskService, typeService);
                break;
            }
            case CreateGoalCommand.PAGE: {
                TypeService typeService = serviceFactory.createTypeService();
                GoalService goalService = serviceFactory.createGoalService();
                command = new CreateGoalCommand(typeService, goalService);
                break;
            }
            case CreateTaskCommand.PAGE: {
                GoalService goalService = serviceFactory.createGoalService();
                TaskService taskService = serviceFactory.createTaskService();
                command = new CreateTaskCommand(goalService, taskService);
                break;
            }
            case LogoutCommand.PAGE: {
                command = new LogoutCommand();
                break;
            }
            case ChangeLanguageCommand.PAGE: {
                command = new ChangeLanguageCommand();
                break;
            }
            case AdminCommand.PAGE: {
                UserService userService = serviceFactory.createUserService();
                command = new AdminCommand(userService);
                break;
            }
            default: {
                UserService userService = serviceFactory.createUserService();
                return new LoginCommand(userService);
            }
        }
        return command;
    }
}
