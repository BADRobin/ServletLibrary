package oleg.bryl.action.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Action {
    Object execute(HttpServletRequest req, HttpServletResponse resp);
}
