package oleg.bryl.service;

import oleg.bryl.dao.*;
import oleg.bryl.entity.Author;
import oleg.bryl.entity.Book;
import oleg.bryl.entity.BookInfo;
import oleg.bryl.entity.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookService {

    public void createAuthor(Author author){

            try(DaoFactory daoFactory = new DaoFactory()) {
                AuthorDaoImpl authorDaoImpl = daoFactory.getAuthorDao();
                authorDaoImpl.insert(author);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillBook(Book book) throws Exception {
        try {
            if (book != null) {
                try (DaoFactory daoFactory = new DaoFactory()) {
                    AuthorDaoImpl authorDaoImpl = daoFactory.getAuthorDao();
                    GenreDaoImpl genreDaoImpl = daoFactory.getGenreDao();
                    List<Author>authors = authorDaoImpl.findAuthorsByBook(book);
                    book.setAuthorList(authors);
                    book.setGenre(genreDaoImpl.findByBook(book));
                }
            }
        } catch (Exception e) {
            throw new Exception("can't fill book", e);
        }
    }

    public List<Genre> getAllGenre() throws Exception {
        List<Genre> genres;

            try (DaoFactory daoFactory = new DaoFactory()) {
                GenreDaoImpl genreDaoImpl = daoFactory.getGenreDao();
                genres = genreDaoImpl.getAll();
                return genres;

        } catch (Exception e) {
            throw new Exception("can't get all genre", e);
        }
    }

    public List<Author> getAllAuthor() throws Exception {
        List<Author> authors;

            try (DaoFactory daoFactory = new DaoFactory()) {
                AuthorDaoImpl authorDaoImpl = daoFactory.getAuthorDao();
                authors = authorDaoImpl.showAllAuthors();
                return authors;

        } catch (Exception e) {
            throw new Exception("can't get all genre", e);
        }
    }

    public List<BookInfo> getListBook(Genre genre, int start, int end) throws Exception {
        List<BookInfo> bookInfoListByGenre = new ArrayList<>();
        try (DaoFactory daoFactory = new DaoFactory()) {

                BookImplDao bookImplDao = daoFactory.getBookDao();
                List<Book> listBooksByGenre = bookImplDao.getLimitBookByGenre(genre, start, end);
                for (Book book : listBooksByGenre) {
                    fillBook(book);
                }
                BookInfoImplDao bookInfoImplDao = daoFactory.getBookInfoDao();
                for (Book book : listBooksByGenre) {
                    BookInfo newBookInfo = new BookInfo();
                    BookInfo bookInfo = bookInfoImplDao.findByBookAmount(book.getId());
                    newBookInfo.setBook(book);
                    newBookInfo.setAmount(bookInfo.getAmount());
                    bookInfoListByGenre.add(newBookInfo);
                }
                return bookInfoListByGenre;
            } catch (Exception e) {
                throw new Exception("can't get list by genre book", e);
            }
        }


    public int getBookCountByGenre(Genre genre) throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {

                BookImplDao bookImplDao = daoFactory.getBookDao();
                int countBooksByGenre = bookImplDao.getBookCountByGenre(genre);
                return countBooksByGenre;
            } catch (Exception e) {
                throw new Exception("can't get count book", e);
            }
        }


    public void addBook(BookInfo bookInfo) throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {

                BookImplDao bookImplDao = daoFactory.getBookDao();
                BookInfoImplDao bookInfoImplDao = daoFactory.getBookInfoDao();
                daoFactory.startTransaction();
                bookImplDao.insert(bookInfo.getBook());
                bookInfoImplDao.insert(bookInfo);
                daoFactory.commitTransaction();
                daoFactory.finishTransaction();
            } catch (Exception e) {
            DaoFactory daoFactory = new DaoFactory();
            daoFactory.rollbackTransaction();
                daoFactory.finishTransaction();
                throw new Exception("can't register book", e);
            }
        }


    public BookInfo findBookById(int id) throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {

                BookImplDao bookImplDao = daoFactory.getBookDao();
                Book book = bookImplDao.findById(id);
                fillBook(book);
                BookInfoImplDao bookInfoImplDao = daoFactory.getBookInfoDao();
                BookInfo bookInfo = bookInfoImplDao.findByBookAmount(book.getId());
                bookInfo.setBook(book);
                return bookInfo;
            } catch (Exception e) {
                throw new Exception("can't get book by ID " + id);
            }
        }


    public List<Book> searchByBookTittle(String item) {
        List<Book> books;
        List<Book> foundBooks = new ArrayList<>();
        try (DaoFactory daoFactory = new DaoFactory()){

                BookImplDao bookImplDao = daoFactory.getBookDao();
                books = bookImplDao.getAllBooks();
                Pattern p = Pattern.compile(item.trim() + "?");
                Pattern small_case = Pattern.compile(item.toLowerCase().trim()+"?");
                for(Book lookForBook:books){
                    Matcher m = p.matcher(lookForBook.getName());
                    Matcher small = small_case.matcher(lookForBook.getName().toLowerCase());
                    if (m.find() || small.find()){
                        Book book;
                        book = bookImplDao.findById(lookForBook.getId());
                        fillBook(book);
                        foundBooks.add(book);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        return foundBooks;
    }

    public List<Author> searchByAuthorName(String item) {
        List<Author> authors;
        List<Author> newAuthors = new ArrayList<>();
        try (DaoFactory daoFactory = new DaoFactory()){

                AuthorDaoImpl authorDaoImpl = daoFactory.getAuthorDao();
                authors = authorDaoImpl.showAllAuthors();
                Pattern p = Pattern.compile(item.trim() + "?");
                Pattern small_case = Pattern.compile(item.toLowerCase().trim()+"?");
                for(Author poet:authors){
                    Matcher m = p.matcher(poet.getLastName());
                    Matcher small = small_case.matcher(poet.getLastName().toLowerCase());
                    if (m.find() || small.find()){
                        Author author;
                        author = authorDaoImpl.findById(poet.getId());
                        newAuthors.add(author);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        return newAuthors;
    }

    public List<Book> searchByAuthorNameAndBookTittle(List<Author> authors) {
        List<Book> books = new ArrayList<>();
        try (DaoFactory daoFactory = new DaoFactory()) {

                BookImplDao bookImplDao = daoFactory.getBookDao();
                books = bookImplDao.getBooksByAuthor(authors);
                for(Book book:books){
                    fillBook(book);
                }
        } catch (Exception e) {
                e.printStackTrace();

        }
            return books;
    }

    public void deleteBook(int id) throws Exception {
        try (DaoFactory daoFactory = new DaoFactory()) {

                BookImplDao bookImplDao = daoFactory.getBookDao();
                BookInfoImplDao bookInfoImplDao = daoFactory.getBookInfoDao();
                Book book = new Book();
                book.setId(id);
                BookInfo bookInfo = bookInfoImplDao.findByBook(book.getId());
                daoFactory.startTransaction();
               bookInfoImplDao.delete(bookInfo);
                bookImplDao.delete(book);
                daoFactory.commitTransaction();
                daoFactory.finishTransaction();
            } catch (Exception e) {
            DaoFactory daoFactory = new DaoFactory();
            daoFactory.rollbackTransaction();
                    daoFactory.finishTransaction();
                    e.getMessage();
                }
            }


    public List<Author> fillAuthors(List<Integer> items) {
        List<Author> authors = new ArrayList<>();
        try (DaoFactory daoFactory = new DaoFactory()) {

                AuthorDaoImpl authorDaoImpl = daoFactory.getAuthorDao();

                for (Integer value : items) {
                    Author author;
                    author = authorDaoImpl.findById(value);
                    authors.add(author);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        return authors;
    }
}