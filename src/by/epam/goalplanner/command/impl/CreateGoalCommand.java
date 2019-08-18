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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */

public class CreateGoalCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String PAGE = "createGoal";

    private final TypeService typeService;
    private final GoalService goalService;

    public CreateGoalCommand(TypeService typeService, GoalService goalService) {
        this.typeService = typeService;
        this.goalService = goalService;
    }

    /**
     * @param req
     * @return instance of {@link ResultCommand} that
     * forward to {@link ProfileCommand}.PAGE with list of tracks in attributes
     * @throws CommandException
     */

    @Override
    public ResultCommand execute(HttpServletRequest req) throws CommandException {
        ResultCommand result;
        try {
            User user = VerificationUser.findUser(req);
            List<Type> defaultTypes = typeService.findSomeTypes();
            List<Type> list = typeService.findAll(user.getId());
            Set<Type> types = new HashSet<>(list);
            if (VariableConstant.POST.getName().equalsIgnoreCase(req.getMethod())) {
                LOGGER.debug("Command " + PAGE + "  began to execute.");
                if (req.getParameter(VariableConstant.DELETE_TYPE.getName()) != null) {
                    String typeName = req.getParameter(DbConstant.TYPE_NAME.getName());
                    if (typeName.equalsIgnoreCase("1")) {
                        String message = ValidateConstant.CANT_DELETE.getName();
                        req.setAttribute(VariableConstant.MESSAGE.getName(), message);
                        showInfo(req, types, defaultTypes);
                        result = new ResultCommand(VariableConstant.CREATE_GOAL_JSP.getName(), true);
                        return result;
                    }
                    long typeId = typeService.findIdByName(typeName);
                    Type tempType = new Type(typeId, typeName);
                    if (defaultTypes.contains(tempType)) {
                        String message = ValidateConstant.CANT_DELETE.getName();
                        req.setAttribute(VariableConstant.MESSAGE.getName(), message);
                        showInfo(req, types, defaultTypes);
                        result = new ResultCommand(VariableConstant.CREATE_GOAL_JSP.getName(), true);
                        return result;
                    } else {
                        typeService.delete(typeId);
                        types.remove(tempType);
                        showInfo(req, types, defaultTypes);
                        result = new ResultCommand(VariableConstant.DO_COMMAND_CREATE_GOAL.getName(), false);
                        return result;
                    }
                } else if (req.getParameter(DbConstant.CREATE_TYPE.getName()) != null) {
                    String newType = req.getParameter(VariableConstant.NEW_TYPE.getName());
                    if (newType.trim().isEmpty()) {
                        String message = ValidateConstant.FIELDS_NULL.getName();
                        req.setAttribute(VariableConstant.MESSAGE.getName(), message);
                        showInfo(req, types, defaultTypes);
                        result = new ResultCommand(VariableConstant.CREATE_GOAL_JSP.getName(), true);
                        return result;
                    }
                    for (int i = 0; i < defaultTypes.size() - 1; i++) {
                        if (newType.equals(defaultTypes.get(i).getName())) {
                            String message = ValidateConstant.TYPE_EXIST.getName();
                            req.setAttribute(VariableConstant.MESSAGE.getName(), message);
                            showInfo(req, types, defaultTypes);
                            result = new ResultCommand(VariableConstant.CREATE_GOAL_JSP.getName(), true);
                            return result;
                        }
                    }
                    typeService.create(newType);
                    long typeId = typeService.findIdByName(newType);
                    Type tempType = new Type(typeId, newType);
                    types.add(tempType);
                    showInfo(req, types, defaultTypes);
                    result = new ResultCommand(VariableConstant.CREATE_GOAL_JSP.getName(), true);
                    return result;
                } else {
                    GoalParametersModel model = GoalParametersModel.getParams(req);
                    String type = req.getParameter(DbConstant.TYPE_NAME.getName());

                    XssProtection protection = XssProtection.profileProtection(model.getName(), model.getDescription());
                    String message = Validator.validateCreateGoal(model.getName(), model.getDescription(), model.getBeginDate(), model.getEndDate(), type);
                    if (message != null) {
                        LOGGER.debug("Data entered incorrectly.");
                        req.setAttribute(VariableConstant.MESSAGE.getName(), message);
                        showInfo(req, types, defaultTypes);
                        result = new ResultCommand(VariableConstant.CREATE_GOAL_JSP.getName(), true);
                        return result;
                    }
                    long typeId = typeService.findIdByName(type);
                    goalService.create(protection.getName(), protection.getDescription(), model.getBeginDate(), model.getEndDate(), user.getId(), typeId);
                }
                result = new ResultCommand(VariableConstant.DO_COMMAND_PROFILE.getName(), false);
                return result;
            }

            showInfo(req, types, defaultTypes);
            result = new ResultCommand(VariableConstant.CREATE_GOAL_JSP.getName(), true);
            return result;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }

    private void showInfo(HttpServletRequest req, Set<Type> set, List<Type> defaultTypes) throws ServiceException {
        set.addAll(defaultTypes);
        req.setAttribute(VariableConstant.TYPES.getName(), set);
    }
}
