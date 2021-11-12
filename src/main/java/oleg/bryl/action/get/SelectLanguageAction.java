package oleg.bryl.action.get;

import oleg.bryl.action.manager.Action;
import oleg.bryl.action.manager.ActionResult;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import static oleg.bryl.action.Constants.*;

public class SelectLanguageAction implements Action {
    private static final Logger log = Logger.getLogger(SelectLanguageAction.class);

    /**
     *
     * @param req
     * @param resp
     * @return
     */
    @Override
    public Object execute(HttpServletRequest req, HttpServletResponse resp) {
        String language = req.getParameter(LANG);
        Config.set(req.getSession(), Config.FMT_LOCALE, new Locale(language));
        Cookie cookie = new Cookie(LANG, language);
        cookie.setMaxAge(HOUR * MINUTE * SEC);
        resp.addCookie(cookie);

        try {
            req.setCharacterEncoding(CHARACTER_ENCODING);
        } catch (UnsupportedEncodingException e) {
            log.info("Ошибка при создании страницы SelectLanguageAction " + e.getMessage());
        }

        return new ActionResult(req.getHeader(REFERER), true);
    }
}
