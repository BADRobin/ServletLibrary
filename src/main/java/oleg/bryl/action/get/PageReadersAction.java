package oleg.bryl.action.get;

import oleg.bryl.action.manager.Action;
import oleg.bryl.action.manager.ActionResult;
import oleg.bryl.entity.User;
import oleg.bryl.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static oleg.bryl.action.Constants.*;

public class PageReadersAction implements Action {
    private static final Logger log = Logger.getLogger(PageReadersAction.class);

    @Override
    public Object execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService userService = new UserService();
        int page = 1;
        int recordPerPage = 10;

        if (req.getParameter(PAGE) != null) {
            page = Integer.parseInt(req.getParameter(PAGE));
        }

        try {
            List<User> readers = userService.getListUsers(page, recordPerPage);

            int noOfRecords = userService.userCount();
            int noOfPages = (int) Math.ceil(noOfRecords * CONVERT_TO_DOUBLE / recordPerPage);

            req.setAttribute(ATT_READERS, readers);
            req.setAttribute(ATT_NO_PAGES, noOfPages);
            req.setAttribute(ATT_CURRENT_PAGE, page);

        } catch (Exception e) {
            log.info("Ошибка при создании страницы PageReadersAction " + e.getMessage());
        }

        return new ActionResult(READERS);
    }
}
