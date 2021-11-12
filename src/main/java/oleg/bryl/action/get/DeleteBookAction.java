package oleg.bryl.action.get;

import oleg.bryl.action.manager.Action;
import oleg.bryl.action.manager.ActionResult;
import oleg.bryl.service.BookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static oleg.bryl.action.Constants.ID_BOOK;
import static oleg.bryl.action.Constants.REFERER;

public class DeleteBookAction implements Action {
    private static final Logger log = Logger.getLogger(DeleteBookAction.class);

    /**
     *
     * @param req
     * @param resp
     * @return
     */
    @Override
    public Object execute(HttpServletRequest req, HttpServletResponse resp) {
        int idBook = Integer.parseInt(req.getParameter(ID_BOOK));
        BookService bookService = new BookService();

        try {
            bookService.deleteBook(idBook);
        } catch (Exception e) {
            log.info("can't delete book: " + e.getMessage());
        }

        return new ActionResult(req.getHeader(REFERER), true);
    }
}
