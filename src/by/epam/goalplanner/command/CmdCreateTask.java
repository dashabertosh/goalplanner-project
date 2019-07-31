package by.epam.goalplanner.command;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.service.GoalService;
import by.epam.goalplanner.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CmdCreateTask implements Cmd {
    public static final String PAGE = "createtask";

    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String DATE = "date";
    private static final String DONE = "done";
    private static final String GOAL_ID = "goal_id";

    private final GoalService goalService;
    private final TaskService taskService;

    public CmdCreateTask(GoalService goalService, TaskService taskService) {
        this.goalService = goalService;
        this.taskService = taskService;
    }

    @Override
    public CmdResult execute(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        CmdResult result;
        if (req.getMethod().equalsIgnoreCase("POST")) {
            String name = req.getParameter(NAME);
            String description = req.getParameter(DESCRIPTION);
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy/MM/dd").parse(req.getParameter(DATE));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            byte done = Byte.parseByte(req.getParameter(DONE));
            Goal goal = goalService.findGoalByName(name).get(0);

            boolean isTask = taskService.create(name, description, date, done, goal.getId());

            result = new CmdResult("profile.jsp", true);
            return result;
        }
        result = new CmdResult("createtask", true);
        return result;
    }
}
