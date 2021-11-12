package oleg.bryl.action.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Action {
    /**
     *
     * @param req
     * @param resp
     * @return
     */
    Object execute(HttpServletRequest req, HttpServletResponse resp);
}
