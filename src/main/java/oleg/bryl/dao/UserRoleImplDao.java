package oleg.bryl.dao;

import oleg.bryl.entity.User;
import oleg.bryl.entity.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleImplDao extends BaseDao<UserRole> {

    private static final String FIND_BY_USER = "select role.id_role ,role.name from role join user on user.id_role  = role.id_role  where user.id_user = ?";
    private static final String FIND_BY_NAME_ROLE = "select * from role  where name = ?";

    public UserRole findByUser(User user) throws Exception {
        UserRole userRole = null;

            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_USER)) {
                statement.setInt(1, user.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        userRole = itemRole(resultSet);
                    }
                }

        } catch (SQLException e) {
            throw new Exception("can't find by user " + this.getClass().getSimpleName(), e);
        }
        return userRole;
    }

    public UserRole findRoleByName(String nameRole) throws Exception{
        UserRole userRole = null;

            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_NAME_ROLE)) {
                statement.setString(1, nameRole);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        userRole = itemRole(resultSet);
                    }
                }

        } catch (SQLException e) {
            throw new Exception("can't find by role " + this.getClass().getSimpleName(), e);
        }
        return userRole;
    }

    private UserRole itemRole(ResultSet resultSet) throws SQLException {
        UserRole userRole = new UserRole();
        userRole.setId(resultSet.getInt(1));
        userRole.setName(resultSet.getString(2));
        return userRole;
    }

    @Override
    public UserRole insert(UserRole item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public UserRole findById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(UserRole item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(UserRole item) {
        throw new UnsupportedOperationException();
    }
}
