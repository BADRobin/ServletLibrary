package oleg.bryl.service;

import oleg.bryl.dao.*;
import oleg.bryl.entity.Book;
import oleg.bryl.entity.Order;
import oleg.bryl.entity.OrderStatus;
import oleg.bryl.entity.User;
import oleg.bryl.util.SqlDate;

import java.util.ArrayList;
import java.util.List;

import static oleg.bryl.action.Constants.NEW;

public class OrderService {

    public void addOrder(Order order) throws Exception {
         String STATUS_ORDER_NEW = NEW;
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                OrderImplDao orderImplDao = daoFactory.getOrderDao();
                OrderStatusImplDao orderStatusImplDao = daoFactory.getOrderStatusDao();
                BookInfoImplDao bookInfoImplDao = daoFactory.getBookInfoDao();
                OrderStatus orderStatus = orderStatusImplDao.findOrderStatusByName(STATUS_ORDER_NEW);
                order.setStatus(orderStatus);
                order.setStartDate(SqlDate.currentDateAndTime());
                daoFactory.startTransaction();
                orderImplDao.insert(order);
                bookInfoImplDao.updateAmount(order.getBooks());
                daoFactory.commitTransaction();
                daoFactory.finishTransaction();
            } catch (Exception e) {
                daoFactory.rollbackTransaction();
                daoFactory.finishTransaction();
                e.printStackTrace();
            }
        }
    }

    public List<Order> showUserOrders(User user) throws Exception {
        List<Order> orders;
        try (DaoFactory daoFactory = new DaoFactory()) {
            OrderImplDao orderImplDao = daoFactory.getOrderDao();
            BookImplDao bookImplDao = daoFactory.getBookDao();
            OrderStatusImplDao orderStatusImplDao = daoFactory.getOrderStatusDao();
            orders = orderImplDao.orderByUser(user);
            for (Order newOrder : orders) {
                List<Book> books = new ArrayList<>();
                OrderStatus orderStatus = orderStatusImplDao.findById(Integer.parseInt(newOrder.getStatus().getName()));
                for (Book book : newOrder.getBooks()) {
                    Book newBook;
                    newBook = bookImplDao.findById(book.getId());
                    books.add(newBook);
                }
                newOrder.setStatus(orderStatus);
                newOrder.setBooks(books);
            }
        }
        return orders;
    }

    public List<Order> showAllOrders(User user) {
        List<Order> orders = null;
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                OrderImplDao orderImplDao = daoFactory.getOrderDao();
                BookImplDao bookImplDao = daoFactory.getBookDao();
                OrderStatusImplDao orderStatusImplDao = daoFactory.getOrderStatusDao();
                orders = orderImplDao.orderByUser(user);
                for (Order order : orders) {
                    List<Book> newBooks = new ArrayList<>();
                    OrderStatus orderStatus = orderStatusImplDao.findById(Integer.parseInt(order.getStatus().getName()));
                    order.setStatus(orderStatus);
                    for (Book book : order.getBooks()) {
                        Book one = bookImplDao.findById(book.getId());
                        newBooks.add(one);
                    }
                    order.setBooks(newBooks);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    public Order changeOrder(int id, int status) {
        Order order = null;
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                OrderImplDao orderImplDao = daoFactory.getOrderDao();
                order = orderImplDao.findById(id);
                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setId(status);
                order.setStatus(orderStatus);
                orderImplDao.update(order);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return order;
    }

    public Order changeOrderCom(int id, int status) throws Exception {
        Order order = null;
        try (DaoFactory daoFactory = new DaoFactory()) {
            try {
                OrderImplDao orderImplDao = daoFactory.getOrderDao();
                BookInfoImplDao bookInfoImplDao = daoFactory.getBookInfoDao();
                List<Book> books = orderImplDao.takeBookByOrderId(id);
                order = orderImplDao.findById(id);
                OrderStatus orderStatus = new OrderStatus();
                orderStatus.setId(status);
                order.setStatus(orderStatus);
                daoFactory.startTransaction();
                orderImplDao.update(order);
                bookInfoImplDao.returnAmount(books);
                daoFactory.commitTransaction();
                daoFactory.finishTransaction();
            } catch (Exception e) {
                daoFactory.rollbackTransaction();
                daoFactory.finishTransaction();
                e.printStackTrace();
            }
        }
        return order;
    }
}
