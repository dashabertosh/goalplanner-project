package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.command.Cmd;
import by.epam.goalplanner.constant.VariableConstant;
import by.epam.goalplanner.exception.DaoException;
import by.epam.goalplanner.service.GoalService;
import by.epam.goalplanner.service.TypeService;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateGoalCmd implements Cmd {
    public static final String PAGE = "createGoal";

    private final TypeService typeService;
    private final GoalService goalService;

    public CreateGoalCmd(TypeService typeService, GoalService goalService) {
        this.typeService = typeService;
        this.goalService = goalService;
    }

    @Override
    public ResultCmd execute(HttpServletRequest req) throws DaoException {
        ResultCmd result;
        if (req.getMethod().equalsIgnoreCase(VariableConstant.POST.getName())) {
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
            User user = VerificationUser.findUser(req);
            long type_id = typeService.findIdByName(name);

            boolean isGoal = goalService.create(name, description, beginDate, endDate, user.getId(), type_id);

            result = new ResultCmd(VariableConstant.PROFILE_JSP.getName(), true);
            return result;
        }
        result = new ResultCmd(VariableConstant.CREATE_GOAL_JSP.getName(), true);
        return result;
    }
}
