package oleg.bryl.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oleg.bryl.entity.Author;
import oleg.bryl.entity.Book;
import oleg.bryl.entity.Genre;
import org.junit.jupiter.api.Test;

class GenreDaoImplTest {
    @Test
    void testInsert() {
        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();

        Genre genre = new Genre();
        genre.setId(1);
        genre.setName("Name");
        assertThrows(UnsupportedOperationException.class, () -> genreDaoImpl.insert(genre));
    }

    @Test
    void testFindById() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString(anyInt())).thenReturn("String");
        when(resultSet.getInt(anyInt())).thenReturn(1);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        doNothing().when(resultSet).close();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);
        Genre actualFindByIdResult = genreDaoImpl.findById(1);
        assertEquals(1, actualFindByIdResult.getId());
        assertEquals("String", actualFindByIdResult.getName());
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(resultSet).close();
        verify(resultSet, atLeast(1)).getInt(anyInt());
        verify(resultSet, atLeast(1)).getString(anyInt());
        verify(resultSet, atLeast(1)).next();
        assertTrue(genreDaoImpl.getAll().isEmpty());
    }

    @Test
    void testFindById2() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString(anyInt())).thenThrow(mock(SQLException.class));
        when(resultSet.getInt(anyInt())).thenReturn(1);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        doNothing().when(resultSet).close();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);
        assertThrows(Exception.class, () -> genreDaoImpl.findById(1));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(resultSet).close();
        verify(resultSet).getInt(anyInt());
        verify(resultSet).getString(anyInt());
        verify(resultSet).next();
    }

    @Test
    void testFindById3() throws Exception {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doThrow(mock(SQLException.class)).when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);
        assertThrows(NullPointerException.class, () -> genreDaoImpl.findById(123));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).close();
    }

    @Test
    void testFindById4() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenThrow(mock(SQLException.class));
        doNothing().when(resultSet).close();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);
        assertThrows(Exception.class, () -> genreDaoImpl.findById(1));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(resultSet).close();
        verify(resultSet).next();
    }

    @Test
    void testFindById5() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        doThrow(mock(SQLException.class)).when(resultSet).close();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);
        assertThrows(Exception.class, () -> genreDaoImpl.findById(1));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(resultSet).close();
    }

    @Test
    void testFindById6() throws Exception {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenThrow(mock(SQLException.class));
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);
        assertThrows(Exception.class, () -> genreDaoImpl.findById(1));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setInt(anyInt(), anyInt());
    }

    @Test
    void testFindById7() throws Exception {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doThrow(mock(SQLException.class)).when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);
        assertThrows(Exception.class, () -> genreDaoImpl.findById(1));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).close();
        verify(preparedStatement).setInt(anyInt(), anyInt());
    }

    @Test
    void testFindById8() throws Exception {
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenThrow(mock(SQLException.class));

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);
        assertThrows(Exception.class, () -> genreDaoImpl.findById(1));
        verify(connection).prepareStatement((String) any());
    }

    @Test
    void testUpdate() {
        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();

        Genre genre = new Genre();
        genre.setId(1);
        genre.setName("Name");
        assertThrows(UnsupportedOperationException.class, () -> genreDaoImpl.update(genre));
    }

    @Test
    void testDelete() {
        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();

        Genre genre = new Genre();
        genre.setId(1);
        genre.setName("Name");
        assertThrows(UnsupportedOperationException.class, () -> genreDaoImpl.delete(genre));
    }

    @Test
    void testGetAll() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString(anyInt())).thenReturn("String");
        when(resultSet.getInt(anyInt())).thenReturn(1);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        doNothing().when(resultSet).close();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);
        List<Genre> actualAll = genreDaoImpl.getAll();
        assertEquals(2, actualAll.size());
        Genre getResult = actualAll.get(1);
        assertEquals("String", getResult.getName());
        assertEquals(1, getResult.getId());
        Genre getResult1 = actualAll.get(0);
        assertEquals("String", getResult1.getName());
        assertEquals(1, getResult1.getId());
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).close();
        verify(preparedStatement).executeQuery();
        verify(resultSet).close();
        verify(resultSet, atLeast(1)).getInt(anyInt());
        verify(resultSet, atLeast(1)).getString(anyInt());
        verify(resultSet, atLeast(1)).next();
    }

    @Test
    void testGetAll2() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString(anyInt())).thenThrow(mock(SQLException.class));
        when(resultSet.getInt(anyInt())).thenReturn(1);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        doNothing().when(resultSet).close();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);
        assertThrows(Exception.class, () -> genreDaoImpl.getAll());
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).close();
        verify(preparedStatement).executeQuery();
        verify(resultSet).close();
        verify(resultSet).getInt(anyInt());
        verify(resultSet).getString(anyInt());
        verify(resultSet).next();
    }

    @Test
    void testGetAll3() throws Exception {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doThrow(mock(SQLException.class)).when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);
        assertThrows(NullPointerException.class, () -> genreDaoImpl.getAll());
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).close();
    }

    @Test
    void testGetAll4() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenThrow(mock(SQLException.class));
        doNothing().when(resultSet).close();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);
        assertThrows(Exception.class, () -> genreDaoImpl.getAll());
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).close();
        verify(preparedStatement).executeQuery();
        verify(resultSet).close();
        verify(resultSet).next();
    }

    @Test
    void testGetAll5() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        doThrow(mock(SQLException.class)).when(resultSet).close();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);
        assertThrows(Exception.class, () -> genreDaoImpl.getAll());
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).close();
        verify(preparedStatement).executeQuery();
        verify(resultSet).close();
    }

    @Test
    void testGetAll6() throws Exception {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenThrow(mock(SQLException.class));
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);
        assertThrows(Exception.class, () -> genreDaoImpl.getAll());
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).close();
        verify(preparedStatement).executeQuery();
    }

    @Test
    void testGetAll7() throws Exception {
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenThrow(mock(SQLException.class));

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);
        assertThrows(Exception.class, () -> genreDaoImpl.getAll());
        verify(connection).prepareStatement((String) any());
    }

    @Test
    void testFindByBook() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString(anyInt())).thenReturn("String");
        when(resultSet.getInt(anyInt())).thenReturn(1);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        doNothing().when(resultSet).close();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);

        Genre genre = new Genre();
        genre.setId(1);
        genre.setName("Name");

        Book book = new Book();
        book.setAuthorList(new ArrayList<Author>());
        book.setIsbn("Isbn");
        book.setDate(mock(Date.class));
        book.setId(1);
        book.setName("Name");
        book.setDescription("The characteristics of someone or something");
        book.setGenre(genre);
        Genre actualFindByBookResult = genreDaoImpl.findByBook(book);
        assertEquals(1, actualFindByBookResult.getId());
        assertEquals("String", actualFindByBookResult.getName());
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(resultSet).close();
        verify(resultSet, atLeast(1)).getInt(anyInt());
        verify(resultSet, atLeast(1)).getString(anyInt());
        verify(resultSet, atLeast(1)).next();
        assertTrue(genreDaoImpl.getAll().isEmpty());
    }

    @Test
    void testFindByBook2() throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString(anyInt())).thenThrow(mock(SQLException.class));
        when(resultSet.getInt(anyInt())).thenReturn(1);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        doNothing().when(resultSet).close();
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        doNothing().when(preparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);

        Genre genre = new Genre();
        genre.setId(1);
        genre.setName("Name");

        Book book = new Book();
        book.setAuthorList(new ArrayList<Author>());
        book.setIsbn("Isbn");
        book.setDate(mock(Date.class));
        book.setId(1);
        book.setName("Name");
        book.setDescription("The characteristics of someone or something");
        book.setGenre(genre);
        assertThrows(Exception.class, () -> genreDaoImpl.findByBook(book));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).close();
        verify(preparedStatement).executeQuery();
        verify(preparedStatement).setInt(anyInt(), anyInt());
        verify(resultSet).close();
        verify(resultSet).getInt(anyInt());
        verify(resultSet).getString(anyInt());
        verify(resultSet).next();
    }

    @Test
    void testFindByBook3() throws Exception {
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        doThrow(mock(SQLException.class)).when(preparedStatement).close();
        Connection connection = mock(Connection.class);
        when(connection.prepareStatement((String) any())).thenReturn(preparedStatement);

        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);

        Genre genre = new Genre();
        genre.setId(1);
        genre.setName("Name");

        Book book = new Book();
        book.setAuthorList(new ArrayList<Author>());
        book.setIsbn("select genre.id_genre ,genre.name from genre join book on book.id_genre  = genre.id_genre  where"
                + " book.id_book = ? ");
        book.setDate(mock(Date.class));
        book.setId(1);
        book.setName("Name");
        book.setDescription("The characteristics of someone or something");
        book.setGenre(genre);
        assertThrows(NullPointerException.class, () -> genreDaoImpl.findByBook(book));
        verify(connection).prepareStatement((String) any());
        verify(preparedStatement).close();
    }
}

