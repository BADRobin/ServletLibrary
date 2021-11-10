package oleg.bryl.action.get;

import oleg.bryl.action.manager.Action;
import oleg.bryl.action.manager.ActionResult;
import oleg.bryl.entity.Order;
import oleg.bryl.entity.User;
import oleg.bryl.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static oleg.bryl.action.Constants.*;

public class ShowOrderUser implements Action {
    private static final Logger log = Logger.getLogger(ShowOrderUser.class);

    @Override
    public Object execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        OrderService orderService = new OrderService();
        User user = new User();
        user.setId((int) session.getAttribute(ATT_USER_ID));
        try {
            List<Order> orders = orderService.showUserOrders(user);
            req.setAttribute(ORDERS, orders);
        } catch (Exception e) {
            log.info("can't show orders by user: " + e.getMessage());
        }

        return new ActionResult(ORDER_PAGE);
    }
}
