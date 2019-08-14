package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.beans.Task;
import by.epam.goalplanner.command.Command;
import by.epam.goalplanner.command.GoalParametersModel;
import by.epam.goalplanner.command.VerificationUser;
import by.epam.goalplanner.command.VariableConstant;
import by.epam.goalplanner.dao.DbConstant;
import by.epam.goalplanner.exception.CommandException;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.GoalService;
import by.epam.goalplanner.service.TaskService;
import by.epam.goalplanner.service.TypeService;
import by.epam.goalplanner.validate.Validator;
import by.epam.goalplanner.validate.XssProtection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ProfileCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String PAGE = "profile";

    private final GoalService goalService;
    private final TaskService taskService;
    private final TypeService typeService;

    public ProfileCommand(GoalService goalService, TaskService taskService, TypeService typeService) {
        this.goalService = goalService;
        this.taskService = taskService;
        this.typeService = typeService;
    }

    @Override
    public ResultCommand execute(HttpServletRequest req) throws CommandException {
        ResultCommand result;
        try {
            if (!VerificationUser.checkUser(req)) return new ResultCommand(VariableConstant.LOGIN_JSP.getName(), true);
            if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
                LOGGER.debug("Command " + PAGE + "  began to execute.");
                if (req.getParameter(VariableConstant.UPDATE_TASK.getName()) != null) {
                    long id = Long.parseLong(req.getParameter(DbConstant.ID_TASK.getName()));
                    taskService.delete(id);
                } else {
                    long id = Long.parseLong(req.getParameter(DbConstant.ID_GOAL.getName()));
                    GoalParametersModel model = GoalParametersModel.getParams(req);
                    long userId = (VerificationUser.findUser(req)).getId();
                    long typeId = typeService.findIdTypeById(Long.parseLong(req.getParameter(DbConstant.ID_GOAL.getName())));

                    XssProtection protection = XssProtection.profileProtection(model.getName(), model.getDescription());
                    String message = Validator.validateProfile(model.getName(), model.getDescription(), model.getBeginDate(), model.getEndDate());
                    if (message != null) {
                        LOGGER.debug("Data entered incorrectly.");
                        req.setAttribute(VariableConstant.MESSAGE.getName(), message);
                        showInfo(req);
                        result = new ResultCommand(VariableConstant.PROFILE_JSP.getName(), true);
                        return result;
                    }

                    Goal goal = new Goal(id, protection.getName(), protection.getDescription(), model.getBeginDate(), model.getEndDate(), userId, typeId);
                    if (req.getParameter(VariableConstant.UPDATE_GOAL.getName()) != null) {
                        goalService.update(goal);
                    } else if (req.getParameter(VariableConstant.DELETE_GOAL.getName()) != null) {
                        goalService.delete(goal.getId());
                    }

                }
            }
            showInfo(req);
            result = new ResultCommand(VariableConstant.PROFILE_JSP.getName(), true);
            return result;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    private void showInfo(HttpServletRequest req) throws ServiceException {
        List<Goal> goals = goalService.findAll(Long.toString((VerificationUser.findUser(req)).getId()));
        for (Goal goal : goals) {
            List<Task> tasks = taskService.findAll(String.valueOf(goal.getId()));
            goal.setTasks(tasks);
        }
        req.setAttribute(VariableConstant.GOALS.getName(), goals);
    }
}
