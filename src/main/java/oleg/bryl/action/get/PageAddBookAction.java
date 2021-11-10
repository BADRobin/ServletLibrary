package oleg.bryl.action.get;

import oleg.bryl.action.manager.Action;
import oleg.bryl.action.manager.ActionResult;
import oleg.bryl.service.BookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static oleg.bryl.action.Constants.*;

public class PageAddBookAction implements Action {
    private static final Logger log = Logger.getLogger(PageAddBookAction.class);

    @Override
    public Object execute(HttpServletRequest req, HttpServletResponse resp) {
        BookService bookService = new BookService();

        try {
            req.setAttribute(ATT_GENRES, bookService.getAllGenre());
            req.setAttribute(ATT_AUTHORS, bookService.getAllAuthor());
        } catch (Exception e) {
            log.info("can't show genres or authors: " + e.getMessage());
        }

        return new ActionResult(NEW_BOOK);
    }
}
