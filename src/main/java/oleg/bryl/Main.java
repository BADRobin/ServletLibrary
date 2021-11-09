package oleg.bryl;

import oleg.bryl.dao.BookInfoImplDao;
import oleg.bryl.dao.DaoFactory;
import oleg.bryl.entity.BookInfo;
import oleg.bryl.service.BookService;

public class Main {
    public static void main(String[] args) throws Exception {
        BookService bookService = new BookService();
        DaoFactory daoFactory = new DaoFactory();
        BookInfoImplDao bookInfoImplDao = daoFactory.getBookInfoDao();
       // BookInfo bookInfo = bookService.findBookById(69);
        BookInfo bookInfo1 = bookInfoImplDao.findByBookAmount(69);

        System.out.println(bookInfo1.getAmount());
    }
}
