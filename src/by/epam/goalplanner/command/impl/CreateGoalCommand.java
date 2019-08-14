package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.Type;
import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.command.Command;
import by.epam.goalplanner.command.GoalParametersModel;
import by.epam.goalplanner.command.VerificationUser;
import by.epam.goalplanner.command.VariableConstant;
import by.epam.goalplanner.dao.DbConstant;
import by.epam.goalplanner.exception.CommandException;
import by.epam.goalplanner.exception.ServiceException;
import by.epam.goalplanner.service.GoalService;
import by.epam.goalplanner.service.TypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CreateGoalCommand implements Command {
    public static final String PAGE = "createGoal";

    private final TypeService typeService;
    private final GoalService goalService;

    public CreateGoalCommand(TypeService typeService, GoalService goalService) {
        this.typeService = typeService;
        this.goalService = goalService;
    }

    @Override
    public ResultCommand execute(HttpServletRequest req) throws CommandException {
        ResultCommand result;
        try {
            if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
                if (req.getParameter(DbConstant.CREATE_TYPE.getName()) != null) {
                    String newType = req.getParameter(VariableConstant.NEW_TYPE.getName());

                    typeService.create(newType);
                    List<Type> types = typeService.findAll();
                    req.setAttribute(VariableConstant.TYPES.getName(), types);

                    result = new ResultCommand(VariableConstant.CREATE_GOAL_JSP.getName(), true);
                    return result;
                }
                GoalParametersModel model = GoalParametersModel.getParams(req);
                User user = VerificationUser.findUser(req);
                if (user != null) {
                    try {
                        String type = req.getParameter(DbConstant.TYPE_NAME.getName());
                        long typeId = typeService.findIdByName(type);
                        goalService.create(model.getName(), model.getDescription(), model.getBeginDate(), model.getEndDate(), user.getId(), typeId);
                    } catch (ServiceException e) {
                        throw new CommandException(e);
                    }
                    result = new ResultCommand(VariableConstant.DO_COMMAND_PROFILE.getName(), false);
                    return result;
                }
            }

            List<Type> types = typeService.findAll();
            req.setAttribute(VariableConstant.TYPES.getName(), types);

            result = new ResultCommand(VariableConstant.CREATE_GOAL_JSP.getName(), true);
            return result;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }


}
