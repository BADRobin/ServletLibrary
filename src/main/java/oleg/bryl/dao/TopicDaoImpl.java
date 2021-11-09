package oleg.bryl.dao;

import oleg.bryl.entity.Topic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicDaoImpl extends BaseDao<Topic> {

    private static final String INSERT = "insert into topic values(id_forum,?)";
    private static final String FIND_ALL_TOPICS = "select id_forum, tittle from topic";

    @Override
    public Topic insert(Topic item) throws Exception {
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(INSERT)) {
                statement.setString(1,item.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception("can't insert " + this.getClass().getSimpleName() + "/" + item, e);
        }
        return item;
    }

    public List<Topic> allTopics() {
        List<Topic> topics = new ArrayList<>();
        try {
            try (PreparedStatement statement = getConnection().prepareStatement(FIND_ALL_TOPICS)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Topic topic = new Topic();
                    topic.setId(resultSet.getInt(1));
                    topic.setName(resultSet.getString(2));
                    topics.add(topic);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return topics;
    }

    @Override
    public Topic findById(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(Topic item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Topic item) {
        throw new UnsupportedOperationException();
    }
}
