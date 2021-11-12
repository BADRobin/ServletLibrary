package oleg.bryl.dao;

import oleg.bryl.entity.Book;
import oleg.bryl.entity.BookInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookInfoImplDao extends BaseDao<BookInfo> {

    private static final String FIND_BY_ID = "select * from book_info  where id_book_info = ?";
    private static final String INSERT = "insert into book_info values(id_book_info,?,?)";
    private static final String UPDATE = "update book_info set amount = ?,id_book = ? where id_book_info = ? ";
    private static final String DELETE = "delete from book_info  where id_book_info = ? ";
    private static final String FIND_BY_BOOK = "select book_info.id_book_info ,book_info.amount from book_info join book on book.id_book  = book_info.id_book  where book.id_book = ? ";
    private static final String UPDATE_AMOUNT = "update book_info set amount = amount - 1 where id_book = ?";
    private static final String RETURN_AMOUNT = "update book_info set amount = amount + 1 where id_book = ?";

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BookInfo findByBookAmount(int id) throws Exception {
        BookInfo bookInfo = new BookInfo();
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_BOOK)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        bookInfo.setId(resultSet.getInt(1));
                        bookInfo.setAmount(resultSet.getInt(2));
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't find by book " + this.getClass().getSimpleName(), e);
        }
        return bookInfo;
    }

    /**
     *
     * @param item
     * @return
     * @throws Exception
     */
    @Override
    public BookInfo insert(BookInfo item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statementBookInfo(statement, item).executeUpdate();
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    item.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't insert " + this.getClass().getSimpleName() + "/" + item, e);
        }
        return item;
    }

    @Override
    public BookInfo findById(int id) throws Exception {
        BookInfo bookInfo = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        bookInfo = itemBookInfo(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't find by id " + this.getClass().getSimpleName(), e);
        }
        return bookInfo;
    }

    /**
     *
     * @param item
     * @throws Exception
     */
    @Override
    public void update(BookInfo item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE)) {
                statementBookInfo(statement, item);
                statement.setInt(3, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("can't update " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public void delete(BookInfo item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("can't delete " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    /**
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private BookInfo itemBookInfo(ResultSet resultSet) throws SQLException {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setId(resultSet.getInt(1));
        bookInfo.setAmount(resultSet.getInt(2));
        return bookInfo;
    }

    /**
     *
     * @param statement
     * @param item
     * @return
     * @throws SQLException
     */
    private PreparedStatement statementBookInfo(PreparedStatement statement, BookInfo item) throws SQLException {
        statement.setInt(1, item.getAmount());
        statement.setInt(2, item.getBook().getId());
        return statement;
    }

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public BookInfo findByBook(int id) throws Exception {
        BookInfo bookInfo = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_BOOK)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        bookInfo = itemBookInfo(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't find by book " + this.getClass().getSimpleName(), e);
        }
        return bookInfo;
    }

    /**
     *
     * @param books
     * @throws Exception
     */
    public void updateAmount(List<Book> books) throws Exception {
        for (Book book : books) {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_AMOUNT)) {
                statement.setInt(1, book.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new Exception("can't update amount for " + this.getClass().getSimpleName() + "/" + book, e);
            }
        }
    }

    /**
     *
     * @param books
     * @throws Exception
     */
    public void returnAmount(List<Book> books) throws Exception {
        for (Book book : books) {
            try (PreparedStatement statement = getConnection().prepareStatement(RETURN_AMOUNT)) {
                statement.setInt(1, book.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new Exception("can't update amount for " + this.getClass().getSimpleName() + "/" + book, e);
            }
        }
    }
}
