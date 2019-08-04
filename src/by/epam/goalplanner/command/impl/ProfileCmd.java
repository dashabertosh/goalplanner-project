package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.beans.Task;
import by.epam.goalplanner.command.Cmd;
import by.epam.goalplanner.constant.SqlConstant;
import by.epam.goalplanner.constant.VariableConstant;
import by.epam.goalplanner.exception.DaoException;
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

public class ProfileCmd implements Cmd {
    public static final String PAGE = "profile";

    private final UserService userService;
    private final GoalService goalService;
    private final TaskService taskService;
    private final TypeService typeService;

    public ProfileCmd(UserService userService, GoalService goalService, TaskService taskService, TypeService typeService) {
        this.userService = userService;
        this.goalService = goalService;
        this.taskService = taskService;
        this.typeService = typeService;
    }

    @Override
    public ResultCmd execute(HttpServletRequest req) throws ServiceException {
        if (!VerificationUser.checkUser(req)) return new ResultCmd(VariableConstant.LOGIN_JSP.getName(), true);
        if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
            if (req.getParameter(VariableConstant.UPDATE_TASK.getName()) != null) {
                Task task = new Task();
                long id = Long.parseLong(req.getParameter(VariableConstant.ID.getName()));
                task.setId(id);
                taskService.delete(task);
            } else {
                long id = Long.parseLong(req.getParameter(VariableConstant.ID.getName()));
                String name = req.getParameter(VariableConstant.NAME.getName());
                String description = req.getParameter(VariableConstant.DESCRIPTION.getName());
                Date beginDate = null;
                Date endDate = null;
                try {
                    beginDate = new SimpleDateFormat(VariableConstant.FORMAT_DATE.getName()).parse(req.getParameter(VariableConstant.BEGIN_DATE.getName()));
                    endDate = new SimpleDateFormat(VariableConstant.FORMAT_DATE.getName()).parse(req.getParameter(VariableConstant.END_DATE.getName()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long userId = VerificationUser.findUser(req).getId();
                long typeId = typeService.findIdByName(req.getParameter(VariableConstant.TYPE_NAME.getName()));
                Goal goal = new Goal(id, name, description, beginDate, endDate, userId, typeId);
                if (req.getParameter(VariableConstant.UPDATE_GOAL.getName()) != null) goalService.update(goal);
                else if (req.getParameter(VariableConstant.DELETE_GOAL.getName()) != null) goalService.delete(goal);

            }
        }
        List<Goal> goals = goalService.findAll();
        req.setAttribute("goals", goals);
        for (Goal goal : goals) {
            String whereGoal = String.format(SqlConstant.WHERE_GOAL_ID.getName(), goal.getId());
            List<Task> tasks = taskService.findAll(whereGoal);
            goal.setTasks(tasks);
        }

        req.setAttribute(VariableConstant.GOALS.getName(), goals);
        return new ResultCmd(VariableConstant.PROFILE_JSP.getName(), false);
    }


}
