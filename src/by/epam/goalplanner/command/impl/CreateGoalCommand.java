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
import by.epam.goalplanner.validate.ValidateConstant;
import by.epam.goalplanner.validate.Validator;
import by.epam.goalplanner.validate.XssProtection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CreateGoalCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
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
                LOGGER.debug("Command " + PAGE + "  began to execute.");
                if (req.getParameter(DbConstant.CREATE_TYPE.getName()) != null) {
                    String newType = req.getParameter(VariableConstant.NEW_TYPE.getName());
                    if (newType.trim().isEmpty()) {
                        String message = ValidateConstant.FIELDS_NULL.getName();
                        req.setAttribute(VariableConstant.MESSAGE.getName(), message);
                        showInfo(req);
                        result = new ResultCommand(VariableConstant.CREATE_GOAL_JSP.getName(), true);
                        return result;
                    }
                    typeService.create(newType);
                    showInfo(req);
                    result = new ResultCommand(VariableConstant.CREATE_GOAL_JSP.getName(), true);
                    return result;
                }
                GoalParametersModel model = GoalParametersModel.getParams(req);
                User user = VerificationUser.findUser(req);
                if (user != null) {
                    try {
                        String type = req.getParameter(DbConstant.TYPE_NAME.getName());
                        long typeId = typeService.findIdByName(type);

                        XssProtection protection = XssProtection.profileProtection(model.getName(), model.getDescription());
                        String message = Validator.validateProfile(model.getName(), model.getDescription(), model.getBeginDate(), model.getEndDate());
                        if (message != null) {
                            LOGGER.debug("Data entered incorrectly.");
                            req.setAttribute(VariableConstant.MESSAGE.getName(), message);
                            showInfo(req);
                            result = new ResultCommand(VariableConstant.CREATE_GOAL_JSP.getName(), true);
                            return result;
                        }

                        goalService.create(protection.getName(), protection.getDescription(), model.getBeginDate(), model.getEndDate(), user.getId(), typeId);
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

    private void showInfo(HttpServletRequest req) throws ServiceException {
        List<Type> types = typeService.findAll();
        req.setAttribute(VariableConstant.TYPES.getName(), types);
    }

}
