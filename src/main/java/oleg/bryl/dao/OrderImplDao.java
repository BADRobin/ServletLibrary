package oleg.bryl.dao;

import oleg.bryl.entity.Book;
import oleg.bryl.entity.Order;
import oleg.bryl.entity.OrderStatus;
import oleg.bryl.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderImplDao extends BaseDao<Order> {

    private static final String FIND_BY_ID = "select * from orders where id_order = ?";
    private static final String INSERT = "insert into orders values(id_order,?,?,?,DEFAULT)";
    private static final String INSERT_ORDER_BOOKS = "insert into order_books values(id_order_books,?,?)";
    private static final String UPDATE_ST = "update orders set status = ? where id_order = ?";
    private static final String DELETE_ORDER_BOOK = "delete from order_books where id_order = ?";
    private static final String FIND_BY_USER_ID = "select id_order, status from orders where id_user = ?";
    private static final String FIND_BOOK_BY_ORDER = "select id_book from order_books where id_order = ?";

    public List<Order> orderByUser(User user) {
        List<Order> orders = new ArrayList<>();
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_USER_ID)) {
                statement.setInt(1, user.getId());
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Order order = new Order();
                    int numb;
                    OrderStatus orderStatus = new OrderStatus();
                    order.setId(resultSet.getInt(1));
                    numb = resultSet.getInt(2);
                    orderStatus.setName(Integer.toString(numb));
                    order.setStatus(orderStatus);
                    List<Book> books = new ArrayList<>();
                    try (PreparedStatement statement2 = getConnection().prepareStatement(FIND_BOOK_BY_ORDER)) {
                        statement2.setInt(1, order.getId());
                        ResultSet resultSet2 = statement2.executeQuery();
                        while (resultSet2.next()) {
                            Book book = new Book();
                            book.setId(resultSet2.getInt(1));
                            books.add(book);
                        }
                    }
                    order.setBooks(books);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public List<Book> takeBookByOrderId(int id) {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement statement2 = getConnection().prepareStatement(FIND_BOOK_BY_ORDER)) {
            statement2.setInt(1, id);
            ResultSet resultSet2 = statement2.executeQuery();
            while (resultSet2.next()) {
                Book book = new Book();
                book.setId(resultSet2.getInt(1));
                books.add(book);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return books;
    }

    @Override
    public Order insert(Order item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statementOrder(statement, item);
                statement.executeUpdate();
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    item.setId(resultSet.getInt(1));
                }
            }
            for (Book book : item.getBooks()) {
                try (PreparedStatement statement = getConnection().prepareStatement(INSERT_ORDER_BOOKS)) {
                    statement.setInt(1, item.getId());
                    statement.setInt(2, book.getId());
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't insert " + this.getClass().getSimpleName() + "/" + item, e);
        }
        return item;
    }

    private PreparedStatement statementOrder(PreparedStatement statement, Order item) throws SQLException {
        statement.setInt(1, item.getUser().getId());
        statement.setInt(2, item.getStatus().getId());
        statement.setDate(3, item.getStartDate());
        return statement;
    }

    @Override
    public Order findById(int id) throws Exception {
        Order order = new Order();
        User user = new User();
        OrderStatus orderStatus = new OrderStatus();
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    order.setId(resultSet.getInt(1));
                    user.setId(resultSet.getInt(2));
                    order.setUser(user);
                    orderStatus.setId(resultSet.getInt(3));
                    order.setStatus(orderStatus);
                    order.setStartDate(resultSet.getDate(4));
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't update order" + this.getClass().getSimpleName() + "/" + e);
        }
        return order;
    }

    @Override
    public void update(Order item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_ST)) {
                statement.setInt(1, item.getStatus().getId());
                statement.setInt(2, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("can't update order" + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public void delete(Order item) {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE_ORDER_BOOK)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
