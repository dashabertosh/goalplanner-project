package by.epam.goalplanner.command;

import by.epam.goalplanner.dao.DbConstant;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * GoalParametersModel
 *
 * @author Dasha Lobkova on 2019-07-25.
 * @version 0.0.1
 */

public class GoalParametersModel {

    private String name;
    private String description;
    private Date beginDate;
    private Date endDate;

    /**
     *
     * @param req DTO containing all data received with {@link javax.servlet.http.HttpServletRequest}
     * @return instance of {@link GoalParametersModel} that
     * contains all parameters for create goal
     */

    public static GoalParametersModel getParams(HttpServletRequest req) {
        GoalParametersModel model = new GoalParametersModel();
        model.name = req.getParameter(DbConstant.NAME_GOAL.getName());
        model.description = req.getParameter(DbConstant.DESCRIPTION.getName());
        model.beginDate = null;
        model.endDate = null;
        try {
            model.beginDate = new SimpleDateFormat(VariableConstant.FORMAT_DATE.getName()).parse(req.getParameter(DbConstant.BEGIN_DATE.getName()));
            model.endDate = new SimpleDateFormat(VariableConstant.FORMAT_DATE.getName()).parse(req.getParameter(DbConstant.END_DATE.getName()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return model;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
