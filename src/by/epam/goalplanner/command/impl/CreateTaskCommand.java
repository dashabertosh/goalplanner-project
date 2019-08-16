package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.command.Command;
import by.epam.goalplanner.command.VariableConstant;
import by.epam.goalplanner.command.VerificationUser;
import by.epam.goalplanner.dao.DbConstant;
import by.epam.goalplanner.exception.CommandException;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.GoalService;
import by.epam.goalplanner.service.TaskService;
import by.epam.goalplanner.validate.ValidateConstant;
import by.epam.goalplanner.validate.Validator;
import by.epam.goalplanner.validate.XssProtection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CreateTaskCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String PAGE = "createTask";

    private final GoalService goalService;
    private final TaskService taskService;

    public CreateTaskCommand(GoalService goalService, TaskService taskService) {
        this.goalService = goalService;
        this.taskService = taskService;
    }

    @Override
    public ResultCommand execute(HttpServletRequest req) throws CommandException {
        ResultCommand result;
        try {
            if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
                LOGGER.debug("Command " + PAGE + "  began to execute.");
                String goalName = req.getParameter(DbConstant.NAME_GOAL.getName());
                if (goalName.equalsIgnoreCase(VariableConstant.CREATE_NEW_GOAL.getName()) || goalName.equalsIgnoreCase(VariableConstant.CREATE_NEW_GOAL_RU.getName())) {
                    result = new ResultCommand(VariableConstant.CREATE_GOAL_JSP.getName(), true);
                    return result;
                }
                String name = req.getParameter(DbConstant.NAME.getName());
                String description = req.getParameter(DbConstant.DESCRIPTION.getName());
                if(req.getParameter(DbConstant.DATE.getName()) == "") {
                    String message = ValidateConstant.TASK_DATE_EMPTY.getName();
                    req.setAttribute(VariableConstant.MESSAGE.getName(), message);
                    showInfo(req);
                    result = new ResultCommand(VariableConstant.CREATE_TASK_JSP.getName(), true);
                    return result;
                }
                Date date = new SimpleDateFormat(VariableConstant.FORMAT_DATE.getName()).parse(req.getParameter(DbConstant.DATE.getName()));
                Goal goal = goalService.findGoalByName(goalName);

                XssProtection protection = XssProtection.profileProtection(name, description);
                String message = Validator.validateCreateTask(name, description, date, goal);
                if (message != null) {
                    LOGGER.debug("Data entered incorrectly.");
                    req.setAttribute(VariableConstant.MESSAGE.getName(), message);
                    showInfo(req);
                    result = new ResultCommand(VariableConstant.CREATE_TASK_JSP.getName(), true);
                    return result;
                }

                taskService.create(protection.getName(), protection.getDescription(), date, goal.getId());
                result = new ResultCommand(VariableConstant.DO_COMMAND_PROFILE.getName(), false);
                return result;
            }
            showInfo(req);
            result = new ResultCommand(VariableConstant.CREATE_TASK_JSP.getName(), true);
            return result;
        } catch (ServiceException | ParseException e) {
            throw new CommandException(e);
        }
    }

    private void showInfo(HttpServletRequest req) throws ServiceException {
        List<Goal> goals = goalService.findAll(Long.toString((VerificationUser.findUser(req)).getId()));
        req.setAttribute(VariableConstant.GOALS.getName(), goals);
    }
}
