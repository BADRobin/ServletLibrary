package oleg.bryl.entity;

import java.sql.Date;

public class User extends BaseEntity {

    private Date registerDate;
    private String password;
    private String email;
    private Person person;
    private UserRole userRole;


    public User() {
        person = new Person();
        userRole = new UserRole();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return getId() + "/" + registerDate + "/" + email + "/" + password +""+person +" "+ userRole;
    }
}
