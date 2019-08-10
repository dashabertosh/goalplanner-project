package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.beans.Task;
import by.epam.goalplanner.command.Command;
import by.epam.goalplanner.command.GoalParametersModel;
import by.epam.goalplanner.command.VerificationUser;
import by.epam.goalplanner.command.VariableConstant;
import by.epam.goalplanner.dao.DBConstant;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.GoalService;
import by.epam.goalplanner.service.TaskService;
import by.epam.goalplanner.service.TypeService;
import by.epam.goalplanner.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ProfileCommand implements Command {
    public static final String PAGE = "profile";

    private final UserService userService;
    private final GoalService goalService;
    private final TaskService taskService;
    private final TypeService typeService;

    public ProfileCommand(UserService userService, GoalService goalService, TaskService taskService, TypeService typeService) {
        this.userService = userService;
        this.goalService = goalService;
        this.taskService = taskService;
        this.typeService = typeService;
    }

    @Override
    public ResultCommand execute(HttpServletRequest req) throws ServiceException {
        if (!VerificationUser.checkUser(req)) return new ResultCommand(VariableConstant.LOGIN_JSP.getName(), true);
        if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
            if (req.getParameter(VariableConstant.UPDATE_TASK.getName()) != null) {
                Task task = new Task();
                long id = Long.parseLong(req.getParameter(DBConstant.ID.getName()));
                task.setId(id);
                taskService.delete(task);
            } else {
                long id = Long.parseLong(req.getParameter(DBConstant.ID.getName()));
                GoalParametersModel model = new GoalParametersModel();
                long userId = Objects.requireNonNull(VerificationUser.findUser(req)).getId();
                long typeId = typeService.findIdByName(req.getParameter(DBConstant.TYPE_NAME.getName()));
                Goal goal = new Goal(id, model.getName(), model.getDescription(), model.getBeginDate(), model.getEndDate(), userId, typeId);
                if (req.getParameter(VariableConstant.UPDATE_GOAL.getName()) != null) goalService.update(goal);
                else if (req.getParameter(VariableConstant.DELETE_GOAL.getName()) != null) goalService.delete(goal);

            }
        }
        List<Goal> goals = goalService.findAll();
        for (Goal goal : goals) {
            List<Task> tasks = taskService.findAll(String.valueOf(goal.getId()));
            goal.setTasks(tasks);
        }
        req.setAttribute(VariableConstant.GOALS.getName(), goals);
        return new ResultCommand(VariableConstant.PROFILE_JSP.getName(), true);
    }


}
