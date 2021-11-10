package oleg.bryl.action.get;

import oleg.bryl.action.manager.Action;
import oleg.bryl.action.manager.ActionResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static oleg.bryl.action.Constants.WELCOME;

public class LogOutAction implements Action {

    @Override
    public Object execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();

        return new ActionResult(WELCOME, true);
    }
}
