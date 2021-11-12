package oleg.bryl.controller;


import oleg.bryl.action.manager.Action;
import oleg.bryl.action.manager.ActionFactory;
import oleg.bryl.action.manager.ActionResult;
import oleg.bryl.action.manager.View;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ControllerServlet.class);

    private ActionFactory actionFactory;

    @Override
    public void init() {
        actionFactory = new ActionFactory();
    }

    /**
     *
     * @param req
     * @param resp
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        Action action = actionFactory.getAction(req);
        log.info("Создан " + action.toString() + " объект по запросу " + req.getMethod() + req.getPathInfo());
        ActionResult result = (ActionResult) action.execute(req, resp);
        log.info("Создан ActionResult: " + result.getView() + " по запросу " + req.getMethod() + req.getPathInfo());

        View view = new View(req, resp);
        log.info("Создан View по запросу " + req.getMethod() + req.getPathInfo());
        view.navigate(result);
        log.info("Осуществлена навигация на View по ActionResult: " + result.getView());
    }
}
