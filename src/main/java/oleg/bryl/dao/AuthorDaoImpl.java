package oleg.bryl.dao;

import oleg.bryl.entity.Author;
import oleg.bryl.entity.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl extends BaseDao<Author> {
    private static final String FIND_BY_ID = "select * from author  where id_author = ?";
    private static final String INSERT = "insert into author values(id_author,?,?,?)";
    private static final String UPDATE = "update author set first_name = ?,last_name = ?,middle_name = ? where id_author = ?";
    private static final String DELETE = "delete from author  where id_author = ?";
    private static final String FIND_ALL_AUTHORS = "select id_author, first_name, last_name, middle_name from author";
    private static final String FIND_ALL_AUTHORS_BY_BOOKS_ID = "select id_author from authors_books where id_book = ?";
    private static final String FIND_ALL_AUTHORS_IN_ONE_BOOK = "select author.first_name ,author.last_name ,author.middle_name from author where id_author = ?";

    public List<Author> findAuthorsByBook(Book book) throws Exception {
        List<Author> authors = new ArrayList<>();
        List<Author>newAuthors = new ArrayList<>();
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_ALL_AUTHORS_BY_BOOKS_ID)) {
                statement.setInt(1, book.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Author author = new Author();
                        author.setId(resultSet.getInt(1));
                        authors.add(author);
                    }
                }
            }
            for (Author auth:authors) {
                try (PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_ALL_AUTHORS_IN_ONE_BOOK)) {
                    preparedStatement.setInt(1, auth.getId());
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            Author author = new Author();
                            author.setFirstName(resultSet.getString(1));
                            author.setLastName(resultSet.getString(2));
                            author.setMiddleName(resultSet.getString(3));
                            newAuthors.add(author);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't find by book " + this.getClass().getSimpleName(), e);
        }
        return newAuthors;
    }

    public List<Author> showAllAuthors(){
        List<Author>authors = new ArrayList<>();
        Author author;
        try {
            try(PreparedStatement statement = getConnection().prepareStatement(FIND_ALL_AUTHORS)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()){
                    author = itemAuthor(resultSet);
                    authors.add(author);
                }
            } catch (SQLException e) {
                e.getMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public Author insert(Author item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT,PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement(statement, item).executeUpdate();
                try(ResultSet resultSet = statement.getGeneratedKeys()){
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
    public Author findById(int id) throws Exception {
        Author author = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        author = itemAuthor(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't find by id  " + this.getClass().getSimpleName(), e);
        }
        return author;
    }

    @Override
    public void update(Author item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE)) {
                statement(statement, item);
                statement.setInt(4, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("can't update " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public void delete(Author item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("can't delete author " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    private PreparedStatement statement(PreparedStatement statement, Author item) throws SQLException {
            statement.setString(1, item.getFirstName());
            statement.setString(2, item.getLastName());
            statement.setString(3, item.getMiddleName());
        return statement;
    }

    private Author itemAuthor(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getInt(1));
        author.setFirstName(resultSet.getString(2));
        author.setLastName(resultSet.getString(3));
        author.setMiddleName(resultSet.getString(4));
        return author;
    }
}
