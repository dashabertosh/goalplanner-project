package by.epam.goalplanner.command;

import by.epam.goalplanner.beans.Goal;
import by.epam.goalplanner.dao.DBConstant;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GoalParametersModel {

    private String name;
    private String description;
    private Date beginDate;
    private Date endDate;

    public static GoalParametersModel getParams(HttpServletRequest req) {
        GoalParametersModel model = new GoalParametersModel();
        model.name = req.getParameter(DBConstant.NAME.getName());
        model.description = req.getParameter(DBConstant.DESCRIPTION.getName());
        model.beginDate = null;
        model.endDate = null;

        try {
            model.beginDate = new SimpleDateFormat(VariableConstant.FORMAT_DATE.getName()).parse(req.getParameter(DBConstant.BEGIN_DATE.getName()));
            model.endDate = new SimpleDateFormat(VariableConstant.FORMAT_DATE.getName()).parse(req.getParameter(DBConstant.END_DATE.getName()));
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
