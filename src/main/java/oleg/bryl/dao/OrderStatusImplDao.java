package oleg.bryl.dao;

import oleg.bryl.entity.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderStatusImplDao extends BaseDao<OrderStatus> {
    private static final String FIND_BY_ID = "select name from order_status where id_order_status = ?";
    private static final String FIND_STATUS_BY_NAME = "select * from order_status  where name = ?";

    @Override
    public OrderStatus insert(OrderStatus item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public OrderStatus findById(int id) {
        OrderStatus orderStatus = new OrderStatus();
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    orderStatus.setName(resultSet.getString(1));
                    orderStatus.setId(id);
                }
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return orderStatus;
    }

    @Override
    public void update(OrderStatus item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(OrderStatus item) {
        throw new UnsupportedOperationException();
    }

    public OrderStatus findOrderStatusByName(String nameStatus) throws Exception {
        OrderStatus orderStatus = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_STATUS_BY_NAME)) {
                statement.setString(1, nameStatus);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        orderStatus = itemOrderStatus(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't find status by name " + this.getClass().getSimpleName(), e);
        }
        return orderStatus;
    }

    private OrderStatus itemOrderStatus(ResultSet resultSet) throws SQLException {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(resultSet.getInt(1));
        orderStatus.setName(resultSet.getString(2));
        return orderStatus;
    }
}
