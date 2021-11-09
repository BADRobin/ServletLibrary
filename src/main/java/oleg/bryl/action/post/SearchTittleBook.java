package oleg.bryl.action.post;

import oleg.bryl.action.manager.Action;
import oleg.bryl.action.manager.ActionResult;
import oleg.bryl.entity.Book;
import oleg.bryl.service.BookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static oleg.bryl.action.Constants.*;

public class SearchTittleBook implements Action {
    private static final Logger log = Logger.getLogger(SearchTittleBook.class);

    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp) {
        String finder = req.getParameter(SEARCHER);
        BookService bookService = new BookService();

        try {
            List<Book> books = bookService.searchByBookTittle(finder);
            req.setAttribute(FIND_BOOKS, books);
        } catch (Exception e) {
            log.info("can't show books by tittle: " + e.getMessage());
        }

        return new ActionResult(FOUND_BOOKS);
    }
}
