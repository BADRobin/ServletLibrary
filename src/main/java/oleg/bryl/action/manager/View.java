package oleg.bryl.action.manager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static oleg.bryl.action.Constants.JSP_FORMAT;
import static oleg.bryl.action.Constants.PATH_TO_JSP;

public class View {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public View(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void navigate(ActionResult result) {
        try {
            if (result.isRedirect()) {
                response.sendRedirect(result.getView());
            } else {
                String path = PATH_TO_JSP + result.getView() + JSP_FORMAT;
                request.getRequestDispatcher(path).forward(request, response);
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
