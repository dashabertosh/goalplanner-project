package by.epam.goalplanner.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class FooterTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().write("<footer>");
            pageContext.getOut().write("<div class=\"footer-copyright text-center py-3\">");
            pageContext.getOut().write("Java Web, 2019  ");
            pageContext.getOut().write("<a href=\"https://github.com/dashabertosh/goalplanner-project\">");
            pageContext.getOut().write("GitHub");
            pageContext.getOut().write("</a>");
            pageContext.getOut().write("</div>");
            pageContext.getOut().write("</footer>");
        } catch (IOException e) {
            LOGGER.error(e);
            throw new JspException(e);
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() {
        return SKIP_BODY;
    }
}
