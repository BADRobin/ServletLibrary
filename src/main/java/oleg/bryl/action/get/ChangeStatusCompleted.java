package oleg.bryl.action.get;

import oleg.bryl.action.manager.Action;
import oleg.bryl.action.manager.ActionResult;
import oleg.bryl.service.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static oleg.bryl.action.Constants.ID_ORDER;
import static oleg.bryl.action.Constants.REFERER;

public class ChangeStatusCompleted implements Action {
    private static final Logger log = Logger.getLogger(ChangeStatusCompleted.class);

    /**
     *
     * @param req
     * @param resp
     * @return
     */
    @Override
    public Object execute(HttpServletRequest req, HttpServletResponse resp) {
        int idOrder = Integer.parseInt(req.getParameter(ID_ORDER));
        OrderService orderService = new OrderService();

        try {
            orderService.changeOrderCom(idOrder, 3);
        } catch (Exception e) {
            log.info("can't change status on completed: " + e.getMessage());
        }

        return new ActionResult(req.getHeader(REFERER), true);
    }
}
