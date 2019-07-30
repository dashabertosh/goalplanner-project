package by.epam.goalplanner.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface Cmd {
    CmdResult execute(HttpServletRequest req, HttpServletResponse res) throws SQLException;
}
