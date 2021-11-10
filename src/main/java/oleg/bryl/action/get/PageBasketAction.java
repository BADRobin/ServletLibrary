package oleg.bryl.action.get;

import oleg.bryl.action.manager.Action;
import oleg.bryl.action.manager.ActionResult;
import oleg.bryl.entity.BookInfo;
import oleg.bryl.service.BookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static oleg.bryl.action.Constants.*;

public class PageBasketAction implements Action {
    private static final Logger log = Logger.getLogger(PageBasketAction.class);

    @Override
    public Object execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        HashSet<Integer> basketList;
        BookService bookService = new BookService();
        List<BookInfo> books = new ArrayList<>();

        if (session.getAttribute(BASKET_LIST) != null) {
            basketList = (HashSet<Integer>) session.getAttribute(BASKET_LIST);
        } else {
            req.setAttribute(BASKET_EMPTY, TRUE);
            return new ActionResult(BASKET);
        }

        for (Integer bookId : basketList) {
            BookInfo bookInfo = null;
            try {
                bookInfo = bookService.findBookById(bookId);
            } catch (Exception e) {
                log.info("can't find book by id: " + e.getMessage());
            }
            if (bookInfo != null) {
                if (bookInfo.getAmount() <= 0) {
                    req.setAttribute(BOOK_NOT_AVAILABLE, TRUE);
                }
                books.add(bookInfo);
            }
        }
        req.setAttribute(BASKET_BOOKS_LIST, books);

        return new ActionResult(BASKET);
    }
}
