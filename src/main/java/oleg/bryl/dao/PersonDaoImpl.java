package oleg.bryl.dao;

import oleg.bryl.entity.Person;
import oleg.bryl.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDaoImpl extends BaseDao<Person> {

    private static final String FIND_BY_ID = "select * from person  where id_person = ?";
    private static final String INSERT = "insert into person values(id_person,?,?,?,?,?)";
    private static final String DELETE = "delete from person  where id_person = ?";
    private static final String FIND_BY_USER = "select person.id_person ,person.first_name ,person.last_name ,person.middle_name ,person.phone ,person.birthday from person join user on user.id_person  = person.id_person  where user.id_user = ?";

    @Override
    public Person insert(Person item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statementPerson(statement, item).executeUpdate();
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
    public Person findById(int id) throws Exception {
        Person person = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_ID)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        person = itemPerson(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't find by id " + this.getClass().getSimpleName(), e);
        }
        return person;
    }

    @Override
    public void update(Person item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Person item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(DELETE)) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("can't delete  " + this.getClass().getSimpleName() + "/" + item, e);
        }
    }

    public Person findByUser(User user) throws Exception {
        Person person = null;
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_BY_USER)) {
                statement.setInt(1, user.getId());
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        person = itemPerson(resultSet);

                    }
                }
            }
        } catch (SQLException e) {
            throw new Exception("can't find by user " + this.getClass().getSimpleName(), e);
        }
        return person;
    }

    private PreparedStatement statementPerson(PreparedStatement statement, Person item) throws SQLException {
        statement.setString(1, item.getFirstName());
        statement.setString(2, item.getLastName());
        statement.setString(3, item.getMiddleName());
        statement.setString(4, item.getPhone());
        statement.setDate(5, item.getBirthday());
        return statement;
    }

    private Person itemPerson(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt(1));
        person.setFirstName(resultSet.getString(2));
        person.setLastName(resultSet.getString(3));
        person.setMiddleName(resultSet.getString(4));
        person.setPhone(resultSet.getString(5));
        person.setBirthday(resultSet.getDate(6));
        return person;
    }
}
