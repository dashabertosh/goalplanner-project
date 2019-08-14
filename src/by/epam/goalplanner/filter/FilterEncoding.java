package by.epam.goalplanner.filter;

import javax.servlet.*;
import java.io.IOException;

public class FilterEncoding implements Filter {

    private String encode = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encode = filterConfig.getInitParameter("encode");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (encode != null && !encode.equalsIgnoreCase(servletRequest.getCharacterEncoding())) servletRequest.setCharacterEncoding(encode);
        if (encode != null && !encode.equalsIgnoreCase(servletResponse.getCharacterEncoding())) servletResponse.setCharacterEncoding(encode);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
