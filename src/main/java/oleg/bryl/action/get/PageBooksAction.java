package oleg.bryl.action.get;

import oleg.bryl.action.manager.Action;
import oleg.bryl.action.manager.ActionResult;
import oleg.bryl.entity.BookInfo;
import oleg.bryl.entity.Genre;
import oleg.bryl.service.BookService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static oleg.bryl.action.Constants.*;


public class PageBooksAction implements Action {
    private static final Logger log = Logger.getLogger(PageBooksAction.class);

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        int genreId;
        int genreState = 1;
        BookService bookService = new BookService();
        Genre genre = new Genre();
        int page;
        int recordPerPage = 20;

        try {
            if (request.getParameter(PAGE) != null) {
                page = Integer.parseInt(request.getParameter(PAGE));
            } else {
                page = 1;
            }
            if (request.getParameter(GENRE_ID) != null) {
                genreId = Integer.parseInt(request.getParameter(GENRE_ID));
                genre.setId(genreId);
                genreState = genreId;
            } else {
                genre.setId(genreState);
            }

            List<BookInfo> books = bookService.getListBook(genre, page, recordPerPage);
            List<Genre> genres = bookService.getAllGenre();
            int noOfRecords = bookService.getBookCountByGenre(genre);
            int noOfPages = (int) Math.ceil(noOfRecords * CONVERT_TO_DOUBLE / recordPerPage);

            request.setAttribute(ATT_BOOKS, books);
            request.setAttribute(ATT_GENRES, genres);
            request.setAttribute(ATT_NO_PAGES, noOfPages);
            request.setAttribute(ATT_CURRENT_PAGE, page);

        } catch (Exception e) {
            log.info("Ошибка при создании страницы PageBooksAction " + e.getMessage());
        }

        return new ActionResult(BOOKS);
    }
}
