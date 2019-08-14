package by.epam.goalplanner.command.impl;

import by.epam.goalplanner.beans.Language;
import by.epam.goalplanner.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {
    public static final String NAME = "change_language";

    private static final String LANGUAGE_ATTRIBUTE = "language";
    private static final String REFERER = "Referer";

    public ChangeLanguageCommand() {
    }

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
