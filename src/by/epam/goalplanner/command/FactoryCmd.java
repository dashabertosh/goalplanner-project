package by.epam.goalplanner.command;

import by.epam.goalplanner.command.impl.*;
import by.epam.goalplanner.service.*;

public class FactoryCmd {
    private final static FactoryCmd FACTORY_CMD = new FactoryCmd();

    private FactoryCmd() {
    }

    public static FactoryCmd getInstance() {
        return FACTORY_CMD;
    }

    public Cmd create(String cmd) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        Cmd command;
        if (cmd == null) {
            UserService userService = serviceFactory.createUserService();
            return new IndexCmd(userService);
        }
        switch (cmd) {
            case LoginCmd.PAGE: {
                UserService userService = serviceFactory.createUserService();
                command = new LoginCmd(userService);
                break;
            }
            case SignUpCmd.PAGE: {
                UserService userService = serviceFactory.createUserService();
                command = new SignUpCmd(userService);
                break;
            }
            case ProfileCmd.PAGE: {
                UserService userService = serviceFactory.createUserService();
                TaskService taskService = serviceFactory.createTaskService();
                GoalService goalService = serviceFactory.createGoalService(); //
                TypeService typeService = serviceFactory.createTypeService();
                command = new ProfileCmd(userService, goalService, taskService, typeService);
                break;
            }
            case CreateGoalCmd.PAGE: {
                TypeService typeService = serviceFactory.createTypeService();
                GoalService goalService = serviceFactory.createGoalService();
                command = new CreateGoalCmd(typeService, goalService);
                break;
            }
            case CreateTaskCmd.PAGE: {
                GoalService goalService = serviceFactory.createGoalService();
                TaskService taskService = serviceFactory.createTaskService();
                command = new CreateTaskCmd(goalService, taskService);
                break;
            }
            default: {
                UserService userService = serviceFactory.createUserService();
                return new IndexCmd(userService);
            }
        }
        return command;
    }
}
