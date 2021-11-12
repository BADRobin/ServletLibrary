package oleg.bryl.action.post;

import oleg.bryl.action.manager.Action;
import oleg.bryl.action.manager.ActionResult;
import oleg.bryl.entity.Author;
import oleg.bryl.entity.Book;
import oleg.bryl.service.BookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static oleg.bryl.action.Constants.*;

public class SearchAuthorBook implements Action {
    private static final Logger log = Logger.getLogger(SearchAuthorBook.class);

    /**
     *
     * @param req
     * @param resp
     * @return
     */
    @Override
    public Object execute(HttpServletRequest req, HttpServletResponse resp)  {
        String finder = req.getParameter(SEARCHER);
        BookService bookService = new BookService();

        try {
            List<Author> authors = bookService.searchByAuthorName(finder);
            List<Book>books = bookService.searchByAuthorNameAndBookTittle(authors);
            req.setAttribute(BOOKS, books);
        } catch (Exception e) {
            log.info("can't show books by author: " + e.getMessage());
        }

        return new ActionResult(FOUND_BOOKS);
    }
}
