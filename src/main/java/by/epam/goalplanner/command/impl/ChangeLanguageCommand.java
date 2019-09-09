package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.Language;
import by.epam.goalplanner.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * ChangeLanguageCommand page
 *
 * @author Dasha Lobkova on 2019-07-18.
 * @version 0.0.1
 */

public class ChangeLanguageCommand implements Command {
    public static final String PAGE = "change_language";
    private static final String LANGUAGE_ATTRIBUTE = "language";
    private static final String REFERER = "Referer";

    public ChangeLanguageCommand() {
    }

    /**
     *
     * @param req DTO containing all data received with {@link javax.servlet.http.HttpServletRequest}
     * @return instance of {@link ResultCommand} that
     * forward to {@link ChangeLanguageCommand}.PAGE
     */
    @Override
    public ResultCommand execute(HttpServletRequest req) {
        String languageString = req.getParameter(LANGUAGE_ATTRIBUTE);
        Language language = Language.parse(languageString);
        HttpSession session = req.getSession();
        session.setAttribute(LANGUAGE_ATTRIBUTE, language.name().toLowerCase());
        String referer = req.getHeader(REFERER);
        return new ResultCommand(referer, false);
    }
}
