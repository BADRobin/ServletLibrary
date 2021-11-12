package oleg.bryl.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class OrderTest {
    @Test
    void testConstructor() {
        Order actualOrder = new Order();
        ArrayList<Book> bookList = new ArrayList<Book>();
        actualOrder.setBooks(bookList);
        actualOrder.setStartDate(mock(Date.class));
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(1);
        orderStatus.setName("Name");
        actualOrder.setStatus(orderStatus);
        Person person = new Person();
        person.setLastName("Doe");
        person.setId(1);
        person.setPhone("4105551212");
        person.setFirstName("Jane");
        person.setMiddleName("Middle Name");
        person.setBirthday(mock(Date.class));
        UserRole userRole = new UserRole();
        userRole.setId(1);
        userRole.setName("Name");
        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setPassword("iloveyou");
        user.setId(1);
        user.setPerson(person);
        user.setUserRole(userRole);
        user.setRegisterDate(mock(Date.class));
        actualOrder.setUser(user);
        assertSame(bookList, actualOrder.getBooks());
        assertSame(orderStatus, actualOrder.getStatus());
        assertSame(user, actualOrder.getUser());
    }

    @Test
    void testConstructor2() {
        Order actualOrder = new Order();
        assertTrue(actualOrder.getBooks().isEmpty());
        assertEquals("0 // 0/null/null/null0/null/null/null/null/null/ 0/null // [] // 0/null // null // ",
                actualOrder.toString());
        assertEquals("0/null/null/null0/null/null/null/null/null/ 0/null", actualOrder.getUser().toString());
    }
}

