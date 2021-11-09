package oleg.bryl.entity;

import java.util.Date;

public class Comment extends BaseEntity {

    private Date date;
    private User user;
    private String message;
    private int numbTopic;

    public int getNumbTopic() {
        return numbTopic;
    }

    public void setNumbTopic(int numbTopic) {
        this.numbTopic = numbTopic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
