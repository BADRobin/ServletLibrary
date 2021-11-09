package oleg.bryl.dao;

import oleg.bryl.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDao<User> {

    private static final String FIND_BY_ID = "select * from user  where id_user = ?";
    private static final String INSERT = "insert into user values(id_user,?,?,?,?,?)";
    private static final String UPDATE = "update user set register_date = ?,password = ?,email = ?,id_person = ?,id_role = ? where id_user = ?";
    private static final String DELETE = "delete from user  where id_user = ?";
    private static final String COUNT_USER = "select count(*) from user";
    private static final String FIND_BY_LOGIN = "select * from user  where email = ?";
    private static final String FIND_BY_LOGIN_PASSWORD = "select * from user  where email = ?  and password = ?";
    private static final String LIMIT_USER = "select * from user limit ?,? ";

    @Override
    public User insert(User item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statementUser(statement, item);
                statement.executeUpdate();
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

    public User findById(int id) throws Exception {
        User user = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        user = itemUser(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't find by id " + this.getClass().getSimpleName(), e);
        }
        return user;
    }

    @Override
    public void update(User item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(UPDATE)) {
                statementUser(statement, item);
                statement.setInt(6, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("can't update  " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    @Override
    public void delete(User item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("can't delete user " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    public int getUserCount() throws Exception {
        int count = 0;
        try (Statement statement = getConnection().createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(COUNT_USER)) {
                while (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't get count user " + this.getClass().getSimpleName(), e);
        }
        return count;
    }

    public User getUser(String login) throws Exception {
        User user = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_LOGIN)) {
                statement.setString(1, login);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        user = itemUser(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't get by login " + this.getClass().getSimpleName(), e);
        }
        return user;
    }

    public User getUser(String login, String password) throws Exception {
        User user = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_LOGIN_PASSWORD)) {
                statement.setString(1, login);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        user = itemUser(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't get by login and pasdword " + this.getClass().getSimpleName(), e);
        }
        return user;
    }

    public List<User> getLimitUsers(int start, int count) throws Exception {
        List<User> list = new ArrayList<>();
        User user;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(LIMIT_USER)) {
                statement.setInt(1, ((start - 1) * count));
                statement.setInt(2, count);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        user = itemUser(resultSet);
                        list.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't get list of user " + this.getClass().getSimpleName(), e);
        }
        return list;
    }

    private PreparedStatement statementUser(PreparedStatement statement, User item) throws SQLException {
        statement.setDate(1, item.getRegisterDate());
        statement.setString(2, item.getPassword());
        statement.setString(3, item.getEmail());
        statement.setInt(4, item.getPerson().getId());
        statement.setInt(5, item.getUserRole().getId());
        return statement;
    }

    private User itemUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(1));
        user.setRegisterDate(resultSet.getDate(2));
        user.setPassword(resultSet.getString(3));
        user.setEmail(resultSet.getString(4));
        return user;
    }
}
