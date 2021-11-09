package oleg.bryl.filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Locale;


import static oleg.bryl.action.Constants.LANG;

public class LocaleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(LANG)) {
                    Locale locale = new Locale(cookie.getValue());
                    Config.set(req.getSession(), Config.FMT_LOCALE, locale);
                }
            }
        }
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
