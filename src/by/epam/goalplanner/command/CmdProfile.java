package by.epam.goalplanner.command;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.beans.Task;
import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.service.GoalService;
import by.epam.goalplanner.service.TaskService;
import by.epam.goalplanner.service.TypeService;
import by.epam.goalplanner.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CmdProfile implements Cmd {
    public static final String PAGE = "profile";

    private final UserService userService;
    private final GoalService goalService;
    private final TaskService taskService;
    private final TypeService typeService;

    public CmdProfile(UserService userService, GoalService goalService, TaskService taskService, TypeService typeService) {
        this.userService = userService;
        this.goalService = goalService;
        this.taskService = taskService;
        this.typeService = typeService;
    }

    @Override
    public CmdResult execute(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        if (!checkUser(req)) return new CmdResult("login.jsp", true);

        if (req.getMethod().equalsIgnoreCase("POST")) {
            if (req.getParameter("Update_task") != null) {
                Task task = new Task();
                long id = Long.parseLong(req.getParameter("id"));
                task.setId(id);
                taskService.delete(task);
            } else {
                long id = Long.parseLong(req.getParameter("id"));
                String name = req.getParameter("name");
                String description = req.getParameter("description");
                long beginDate = Long.parseLong(req.getParameter("begin_date"));
                long endDate = Long.getLong(req.getParameter("end_date"));
                long userId = findUser(req).getId();
                long typeId = typeService.findIdByName(req.getParameter("type_name"));
                Goal goal = new Goal(id, name, description, beginDate, endDate, userId, typeId);
                if (req.getParameter("Update_goal") != null) goalService.update(goal);
                else if (req.getParameter("Delete_goal") != null) goalService.delete(goal);

            }
        }
        List<Goal> goals = goalService.findAll();

        for (Goal goal: goals) {
            String whereGoal = String.format(" WHERE `task`.`goal_id` = '%d'", goal.getId());
            List<Task> tasks = taskService.findAll(whereGoal);
            goal.setTasks(tasks);
        }

        req.setAttribute("goals", goals);
        return new CmdResult("profile.jsp", false);
    }

    private boolean checkUser(HttpServletRequest req) {
       return findUser(req) != null;
    }

    private User findUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session != null) {
            Object oUser = session.getAttribute("user");
            if (oUser != null) {
                return (User) oUser;
            }
        }
        return null;
    }
}