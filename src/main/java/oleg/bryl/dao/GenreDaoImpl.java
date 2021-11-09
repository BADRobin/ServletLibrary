package oleg.bryl.dao;

import oleg.bryl.entity.Book;
import oleg.bryl.entity.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoImpl extends BaseDao<Genre> {

    private static final String FIND_BY_ID = "select * from genre  where id_genre = ?";
    private static final String SELECT_ALL = "select * from genre";
    private static final String FIND_BY_BOOK = "select genre.id_genre ,genre.name from genre join book on book.id_genre  = genre.id_genre  where book.id_book = ? ";

    @Override
    public Genre insert(Genre item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Genre findById(int id) throws Exception {
        Genre genre = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        genre = itemGenre(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't find by id " + this.getClass().getSimpleName(), e);
        }
        return genre;
    }

    @Override
    public void update(Genre item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Genre item) {
        throw new UnsupportedOperationException();

    }

    public List<Genre> getAll() throws Exception {
        List<Genre> list = new ArrayList<>();
        Genre genre;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(SELECT_ALL)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        genre = itemGenre(resultSet);
                        list.add(genre);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't get all list " + this.getClass().getSimpleName(), e);
        }
        return list;
    }

    public Genre findByBook(Book book) throws Exception {
        Genre genre = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_BOOK)) {
                statement.setInt(1, book.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        genre = itemGenre(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't find by book " + this.getClass().getSimpleName(), e);
        }
        return genre;
    }

    private Genre itemGenre(ResultSet resultSet) throws SQLException {
        Genre genre = new Genre();
        genre.setId(resultSet.getInt(1));
        genre.setName(resultSet.getString(2));
        return genre;
    }
}
