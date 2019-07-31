package by.epam.goalplanner.command;

import by.epam.goalplanner.beans.User;
import by.epam.goalplanner.service.GoalService;
import by.epam.goalplanner.service.TypeService;
import sun.java2d.cmm.CMSManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CmdCreateGoal implements Cmd {
    public static final String PAGE = "creategoal";

    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String BEGIN_DATE = "begin_date";
    private static final String END_DATE = "end_date";
    private static final String USER_ID = "user_id";
    private static final String TTPE_ID = "type_id";

    private final TypeService typeService;
    private final GoalService goalService;

    public CmdCreateGoal(TypeService typeService, GoalService goalService) {
        this.typeService = typeService;
        this.goalService = goalService;
    }

    @Override
    public CmdResult execute(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        CmdResult result;
        if (req.getMethod().equalsIgnoreCase("POST")) {
            String name = req.getParameter(NAME);
            String descrption = req.getParameter(DESCRIPTION);
            Date beginDate = null;
            Date endDate = null;
            try {
                beginDate = new SimpleDateFormat("yyyy/MM/dd").parse(req.getParameter(BEGIN_DATE));
                endDate = new SimpleDateFormat("yyyy/MM/dd").parse(req.getParameter(END_DATE));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            User user = Util.findUser(req);
            long type_id = typeService.findIdByName(name);

            boolean isGoal = goalService.create(name, descrption, beginDate, endDate, user.getId(), type_id);

            result = new CmdResult("profile.jsp", true);
            return result;
        }
        result = new CmdResult("creategoal.jsp", true);
        return result;
    }
}
