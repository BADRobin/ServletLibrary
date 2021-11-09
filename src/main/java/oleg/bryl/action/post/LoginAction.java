package oleg.bryl.action.post;

import oleg.bryl.action.manager.Action;
import oleg.bryl.action.manager.ActionResult;
import oleg.bryl.entity.User;
import oleg.bryl.service.UserService;
import oleg.bryl.util.Hasher;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static oleg.bryl.action.Constants.*;

public class LoginAction implements Action {
    private static final Logger log = Logger.getLogger(LoginAction.class);

    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = new UserService();
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);

        try {
            User user = userService.findByLoginPassword(login, Hasher.MD5(password));
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute(ATT_USER_ID, user.getId());
                session.setAttribute(ATT_ROLE, user.getUserRole().getName());
                session.setAttribute(ATT_ROLE_ID, user.getUserRole().getId());
                session.setAttribute(ATT_NAME, user.getPerson().getFirstName());
                return new ActionResult(MAIN, true);
            } else {
                req.setAttribute(LOGIN_ERROR, true);
                return new ActionResult(WELCOME);
            }
        } catch (Exception e) {
            log.info("Ошибка при создании страницы LoginAction " + e.getMessage());
        }
        return null;
    }
}
