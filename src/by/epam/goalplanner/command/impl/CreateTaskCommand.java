package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.command.Command;
import by.epam.goalplanner.command.VariableConstant;
import by.epam.goalplanner.dao.DBConstant;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.GoalService;
import by.epam.goalplanner.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CreateTaskCommand implements Command {
    public static final String PAGE = "createTask";

    private final GoalService goalService;
    private final TaskService taskService;

    public CreateTaskCommand(GoalService goalService, TaskService taskService) {
        this.goalService = goalService;
        this.taskService = taskService;
    }

    @Override
    public ResultCommand execute(HttpServletRequest req) throws ServiceException {
        ResultCommand result;
        if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
            String name = req.getParameter(DBConstant.NAME.getName());
            String description = req.getParameter(DBConstant.DESCRIPTION.getName());
            Date date = null;
            try {
                date = new SimpleDateFormat(VariableConstant.FORMAT_DATE.getName()).parse(req.getParameter(DBConstant.DATE.getName()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String goal = req.getParameter("goal_name");
            long goalId = goalService.findGoalIdByName(goal);
            taskService.create(name, description, date, goalId);

            result = new ResultCommand(VariableConstant.DO_COMMAND_PROFILE.getName(), false);
            return result;
        }
        List<Goal> goals = goalService.findAll();
        req.setAttribute(VariableConstant.GOALS.getName(), goals);
        result = new ResultCommand(VariableConstant.CREATE_TASK_JSP.getName(), true);
        return result;
    }
}
