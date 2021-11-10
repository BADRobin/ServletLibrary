package oleg.bryl.action.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageAction implements Action {
    private final ActionResult result;

    public ShowPageAction(String page) {
        result = new ActionResult(page);
    }

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse resp) {
        return result;
    }
}
