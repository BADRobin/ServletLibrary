package oleg.bryl.action.post;

import oleg.bryl.action.manager.Action;
import oleg.bryl.action.manager.ActionResult;
import oleg.bryl.entity.User;
import oleg.bryl.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static oleg.bryl.action.Constants.*;

public class DeleteProfileAction implements Action {
    private static final Logger log = Logger.getLogger(DeleteProfileAction.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = new UserService();
        User user = new User();
        int idUser = Integer.valueOf(req.getParameter(READER_ID));

        try {
            user = userService.findUserById(idUser);
            userService.deleteUser(user);
        } catch (Exception e) {
            log.info("Ошибка при создании страницы DeleteProfileAction " + e.getMessage());
        }

        if (user.getUserRole().getName().equals(ADMIN)) {
            return new ActionResult(WELCOME);
        }

        return new ActionResult(READERS, true);
    }
}
