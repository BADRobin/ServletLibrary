package oleg.bryl.filters;

import javax.servlet.*;
import java.io.IOException;

import static oleg.bryl.action.Constants.CHARACTER_ENCODING;


public class EncodingFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(CHARACTER_ENCODING);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}

}
