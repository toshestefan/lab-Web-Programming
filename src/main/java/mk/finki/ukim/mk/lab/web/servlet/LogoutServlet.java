package mk.finki.ukim.mk.lab.web.servlet;

import mk.finki.ukim.mk.lab.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name="logout-servlet", urlPatterns = "/logout1")
public class LogoutServlet extends HttpServlet {

    private final OrderService orderService;

    public LogoutServlet(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s=req.getSession();
        orderService.placeOrder(
                (String) s.getAttribute("color"),
                (String) s.getAttribute("size"),
                (String) s.getAttribute("clientName"),
                (String) s.getAttribute("clientAddress"), (long) s.hashCode());
        s.invalidate();
        resp.sendRedirect("/");
    }
}
