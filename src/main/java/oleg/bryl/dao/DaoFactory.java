package oleg.bryl.dao;

import oleg.bryl.jdbc.ConnectionPool;
import oleg.bryl.jdbc.exception.ResourcesException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;


public class DaoFactory implements AutoCloseable {
    private static final Logger log = Logger.getLogger(DaoFactory.class);
    private final ConnectionPool connectionPool;
    private Connection connection;

    public DaoFactory() {
        connectionPool = ConnectionPool.getInstance();
        try {
            connection = connectionPool.getConnection();
            log.info("Создан DaoFactory");
        } catch (ResourcesException e) {
            e.printStackTrace();
        }
    }

    public PersonDaoImpl getPersonDao() {
        PersonDaoImpl personDaoImpl = new PersonDaoImpl();
        personDaoImpl.setConnection(connection);
        log.info("Предоставлен PersonDao");
        return personDaoImpl;
    }

    public TopicDaoImpl getTopicDaoImpl(){
        TopicDaoImpl topicDao = new TopicDaoImpl();
        topicDao.setConnection(connection);
        log.info("Предоставлен TopicImpl");
        return topicDao;
    }

//    public CommentDaoImpl getCommentDaoImpl(){
//        CommentDaoImpl commentDao = new CommentDaoImpl();
//        commentDao.setConnection(connection);
//        log.info("Предоставлен CommentDao");
//        return commentDao;
//    }

    public UserDaoImpl getUserDao() {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        userDaoImpl.setConnection(connection);
        log.info("Предоставлен UserDao");
        return userDaoImpl;
    }

    public UserRoleImplDao getUserRoleDao() {
        UserRoleImplDao userRoleImplDao = new UserRoleImplDao();
        userRoleImplDao.setConnection(connection);
        log.info("Предоставлен UserRoleDao");
        return userRoleImplDao;
    }

    public AuthorDaoImpl getAuthorDao() {
        AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();
        authorDaoImpl.setConnection(connection);
        log.info("Предоставлен AuthorDao");
        return authorDaoImpl;
    }

    public GenreDaoImpl getGenreDao() {
        GenreDaoImpl genreDaoImpl = new GenreDaoImpl();
        genreDaoImpl.setConnection(connection);
        log.info("Предоставлен GenreDao");
        return genreDaoImpl;
    }

    public BookImplDao getBookDao() {
        BookImplDao bookImplDao = new BookImplDao();
        bookImplDao.setConnection(connection);
        log.info("Предоставлен BookDao");
        return bookImplDao;
    }

    public BookInfoImplDao getBookInfoDao() {
        BookInfoImplDao bookInfoImplDao = new BookInfoImplDao();
        bookInfoImplDao.setConnection(connection);
        return bookInfoImplDao;
    }

    public OrderImplDao getOrderDao() {
        OrderImplDao orderImplDao = new OrderImplDao();
        orderImplDao.setConnection(connection);
        log.info("Предоставлен OrderDao");
        return orderImplDao;
    }

    public OrderStatusImplDao getOrderStatusDao() {
        OrderStatusImplDao orderStatusImplDao = new OrderStatusImplDao();
        orderStatusImplDao.setConnection(connection);
        log.info("Предоставлен OrderStatusDao");
        return orderStatusImplDao;
    }

    public void returnConnect() {
        connectionPool.returnConnection(connection);
    }

    public void startTransaction() throws Exception {
        try {
            connection.setAutoCommit(false);
            log.info("Начата транзакция");
        } catch (SQLException e) {
            throw new Exception("Cannot start transaction", e);
        }
    }

    public void commitTransaction() throws Exception {
        try {
            connection.commit();
            log.info("Транзакция проведена");
        } catch (SQLException e) {
            throw new Exception("Cannot commit transaction", e);
        }
    }

    public void finishTransaction() throws Exception {
        try {
            connection.setAutoCommit(true);
            log.info("AutoCommit is true");
        } catch (SQLException e) {
            throw new Exception("Cannot setAutoCommit true", e);
        }
    }

    public void rollbackTransaction() throws Exception {
        try {
            connection.rollback();
            log.info("Транзакция отменена");
        } catch (SQLException e) {
            throw new Exception("Cannot rollback transaction", e);
        }
    }

    @Override
    public void close() {
        returnConnect();
        log.info("Закрыт DaoFactory");
    }
}
