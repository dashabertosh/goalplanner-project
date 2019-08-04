package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.command.Cmd;
import by.epam.goalplanner.constant.VariableConstant;
import by.epam.goalplanner.service.GoalService;
import by.epam.goalplanner.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CreateTaskCmd implements Cmd {
    public static final String PAGE = "createTask";

    private final GoalService goalService;
    private final TaskService taskService;

    public CreateTaskCmd(GoalService goalService, TaskService taskService) {
        this.goalService = goalService;
        this.taskService = taskService;
    }

    @Override
    public ResultCmd execute(HttpServletRequest req) {
        ResultCmd result;
        if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
            String name = req.getParameter(VariableConstant.NAME.getName());
            String description = req.getParameter(VariableConstant.DESCRIPTION.getName());
            Date date = null;
            try {
                date = new SimpleDateFormat(VariableConstant.FORMAT_DATE.getName()).parse(req.getParameter(VariableConstant.DATE.getName()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Goal goal = goalService.findGoalByName(name).get(0); //

            boolean isTask = taskService.create(name, description, date, goal.getId());

            result = new ResultCmd(VariableConstant.PROFILE_JSP.getName(), true);
            return result;
        }
        result = new ResultCmd(VariableConstant.CREATE_TASK_JSP.getName(), true);
        return result;
    }
}
